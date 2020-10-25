package org.sunt.customize.service;

import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomizeMasterDataService {

    @Autowired
    private MongoClient mongoClient;

    public Integer createRecord() {
        return 0;
    }

    public Integer updateRecord() {
        return 0;
    }

    public Integer deleteRecord() {
        return 0;
    }

}
