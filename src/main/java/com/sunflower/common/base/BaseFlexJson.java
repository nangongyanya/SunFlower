package com.sunflower.common.base;

import flexjson.JSONSerializer;

public class BaseFlexJson
{
  public String serializeToJSONString()
    throws Exception
  {
    return new JSONSerializer().deepSerialize(this);
  }
}
