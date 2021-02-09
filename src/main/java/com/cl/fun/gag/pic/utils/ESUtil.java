package com.cl.fun.gag.pic.utils;

import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ESUtil {
    @Autowired
    private TransportClient transportClient;

    private static TransportClient client;

    @PostConstruct
    public void init(){
        client = this.transportClient;
    }

    // 根据ID查询
    public static Map<String,Object> selectDataById(String index,String type,String id ,String fields){
        GetRequestBuilder getRequestBuilder = client.prepareGet(index, type, id);
        if (!StringUtils.isEmpty(fields)) {
            getRequestBuilder.setFetchSource(fields.split(","), null);
        }
        GetResponse documentFields = getRequestBuilder.execute().actionGet();
        return documentFields.getSource();
    }

    // 全文查询
    public static List<Map<String,Object>> selectAllData(String index, String type, QueryBuilder queryBuilder, Integer size,
                                                         String fields, String orderField, String highlightField){
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
        if (!StringUtils.isEmpty(type)) {
            searchRequestBuilder.setTypes(type.split(","));
        }
        if (!StringUtils.isEmpty(highlightField)) {
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field(highlightField);
            searchRequestBuilder.highlighter(highlightBuilder);
        }
        searchRequestBuilder.setQuery(queryBuilder);
        // 判断field，需要显示字段不为空
        if(!StringUtils.isEmpty(fields)){
            searchRequestBuilder.setFetchSource(fields.split(","), null);
        }
        if (!StringUtils.isEmpty(orderField)) {
            searchRequestBuilder.addSort(orderField, SortOrder.DESC);
        }
        if (size!=null && size>0){
            searchRequestBuilder.setSize(size);
        }
        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
        // 查询成功，解析对象
        if ("OK".equals(searchResponse.status().toString().toUpperCase())) {
            return handleHighlightText(searchResponse, highlightField);
        }
        return null;
    }

    // 解析对象
    private static List<Map<String,Object>> handleHighlightText(SearchResponse searchResponse, String highlightField){
        List<Map<String, Object>> sourceList = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();

        for (SearchHit searchHit: searchResponse.getHits().getHits()) {
            searchHit.getSourceAsMap().put("id", searchHit.getId());
            if (!StringUtils.isEmpty(highlightField)) {
                Text[] fragments = searchHit.getHighlightFields().get(highlightField).getFragments();
                if (fragments!=null){
                    for (Text text : fragments) {
                        stringBuffer.append(text.toString());
                    }
                    // 遍历高亮显示的结果集
                    searchHit.getSourceAsMap().put(highlightField, stringBuffer.toString());
                }
            }
            sourceList.add(searchHit.getSourceAsMap());
        }
        return sourceList;
     }

}
