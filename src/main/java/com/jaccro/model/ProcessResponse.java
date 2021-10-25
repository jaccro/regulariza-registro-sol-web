package com.jaccro.model;

public class ProcessResponse {
  private String id;
  private String xml;

  public ProcessResponse() {
  }
  
  public ProcessResponse(String id, String xml) {
    this.id = id;
    this.xml = xml;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getXml() {
    return xml;
  }

  public void setXml(String xml) {
    this.xml = xml;
  }
}
