package com.mybatis.generator.plugins;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.List;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

public class RenameJavaModelPlugins extends PluginAdapter {

  private String suffixString;

  @Override
  public boolean validate(List<String> warnings) {

    String suffixStringTemp = properties.getProperty("suffixString");

    boolean valid = stringHasValue(suffixStringTemp);

    if (!valid) {
      warnings.add(getString("ValidationError.18",
          "RenameJavaModelPlugins",
          "suffixString"));
    } else {
      suffixString = suffixStringTemp.substring(0, 1).toUpperCase()
          .concat(suffixStringTemp.substring(1, suffixStringTemp.length()));
    }

    return valid;
  }

  @Override
  public void initialized(IntrospectedTable introspectedTable) {
    introspectedTable.setBaseRecordType(introspectedTable.getBaseRecordType().concat(suffixString));
  }
}
