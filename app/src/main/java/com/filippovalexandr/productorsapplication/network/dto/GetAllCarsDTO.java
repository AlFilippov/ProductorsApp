package com.filippovalexandr.productorsapplication.network.dto;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "model_id",
        "year",
        "owner"
})
public class GetAllCarsDTO {

    @JsonProperty("id")
    private String id;
    @JsonProperty("model_id")
    private long modelId;
    @JsonProperty("year")
    private long year;
    @JsonProperty("owner")
    private String owner;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("model_id")
    public long getModelId() {
        return modelId;
    }

    @JsonProperty("model_id")
    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    @JsonProperty("year")
    public long getYear() {
        return year;
    }

    @JsonProperty("year")
    public void setYear(long year) {
        this.year = year;
    }

    @JsonProperty("owner")
    public String getOwner() {
        return owner;
    }

    @JsonProperty("owner")
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}