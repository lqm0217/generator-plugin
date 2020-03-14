package com.mybatis.generator.plugins;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;

/**
 * @author acn
 */
public class RepositoryAnnotationPlugin extends PluginAdapter {

  private final static Map<String, String> ANNOTATION_IMPORTS;

  private boolean enableMapper = false;
  private boolean enableRepository = false;

  static {
    ANNOTATION_IMPORTS = new HashMap<>();
    ANNOTATION_IMPORTS.put("@Mapper", "org.apache.ibatis.annotations.Mapper");
    ANNOTATION_IMPORTS.put("@Repository", "org.springframework.stereotype.Repository");
  }

  @Override
  public boolean validate(List<String> warnings) {

    String enableMapperValidate = properties.getProperty("enableMapper");
    String enableRepositoryValidate = properties.getProperty("enableRepository");

    if (!isBooleanString(enableMapperValidate)) {
      warnings.add(getString("ValidationError.18",
          "MapperAnnotationPlugin",
          "enableMapper"));
    } else {
      enableMapper = true;
      return true;
    }

    if (!isBooleanString(enableRepositoryValidate)) {
      warnings.add(getString("ValidationError.18",
          "MapperAnnotationPlugin",
          "enableRepository"));
    } else {
      warnings.clear();
      enableRepository = true;
      return true;
    }

    return false;
  }

  @Override
  public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {

    if (enableMapper) {
      interfaze.addImportedType(new FullyQualifiedJavaType(ANNOTATION_IMPORTS.get("@Mapper")));
      interfaze.addAnnotation("@Mapper");
    }
    if (enableRepository) {
      interfaze.addImportedType(new FullyQualifiedJavaType(ANNOTATION_IMPORTS.get("@Repository")));
      interfaze.addAnnotation("@Repository");
    }

    return true;
  }

  private boolean isBooleanString(String param) {

    if (param != null && !"".equals(param.trim()) && Boolean.parseBoolean(param)) {
      return true;
    }
    return false;
  }
}
