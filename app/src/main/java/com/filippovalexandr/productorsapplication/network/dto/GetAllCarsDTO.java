package com.filippovalexandr.productorsapplication.network.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class GetAllCarsDTO extends RealmObject {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("model_id")
    @Expose
    private int modelId;
    @SerializedName("year")
    @Expose
    private long year;
    @SerializedName("owner")
    @Expose
    private String owner;
    private int idData;
    private String title_model;

    public String getTitle_model() {
        return title_model;
    }

    public void setTitle_model(String title_model) {
        this.title_model = title_model;
    }

    public int getIdData() {
        return idData;
    }

    public void setIdData(int idData) {
        this.idData = idData;
    }

    /**
     * No args constructor for use in serialization
     */
    public GetAllCarsDTO() {
    }

    /**
     * @param modelId
     * @param id
     * @param owner
     * @param year
     */
    public GetAllCarsDTO(String id, int modelId, long year, String owner) {
        super();
        this.id = id;
        this.modelId = modelId;
        this.year = year;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

}