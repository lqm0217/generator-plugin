package com.mybatis.generator.plugins;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * @author acn
 */
public class LombokPlusPlugin extends PluginAdapter {

  private final static Map<String, String> ANNOTATION_IMPORTS;

  static {
    ANNOTATION_IMPORTS = new HashMap<>();
    ANNOTATION_IMPORTS.put("@Getter", "lombok.Getter");
    ANNOTATION_IMPORTS.put("@Setter", "lombok.Setter");
  }

  @Override
  public boolean validate(List<String> warnings) {
    return true;
  }

  /**
   * Intercepts base record class generation
   *
   * @param topLevelClass
   * @param introspectedTable
   * @return
   */
  @Override
  public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
      IntrospectedTable introspectedTable) {
    addAnnotation(topLevelClass);
    return true;
  }

  /**
   * Intercepts primary key class generation
   *
   * @param topLevelClass
   * @param introspectedTable
   * @return
   */
  @Override
  public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass,
      IntrospectedTable introspectedTable) {
    addAnnotation(topLevelClass);
    return true;
  }

  /**
   * Intercepts "record with blob" class generation
   *
   * @param topLevelClass
   * @param introspectedTable
   * @return
   */
  @Override
  public boolean modelRecordWithBLOBsClassGenerated(
      TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    addAnnotation(topLevelClass);
    return true;
  }

  /**
   * Prevents all getters from being generated. See SimpleModelGenerator
   *
   * @param method
   * @param topLevelClass
   * @param introspectedColumn
   * @param introspectedTable
   * @param modelClassType
   * @return
   */
  @Override
  public boolean modelGetterMethodGenerated(Method method,
      TopLevelClass topLevelClass,
      IntrospectedColumn introspectedColumn,
      IntrospectedTable introspectedTable,
      ModelClassType modelClassType) {
    return false;
  }

  /**
   * Prevents all setters from being generated See SimpleModelGenerator
   *
   * @param method
   * @param topLevelClass
   * @param introspectedColumn
   * @param introspectedTable
   * @param modelClassType
   * @return
   */
  @Override
  public boolean modelSetterMethodGenerated(Method method,
      TopLevelClass topLevelClass,
      IntrospectedColumn introspectedColumn,
      IntrospectedTable introspectedTable,
      ModelClassType modelClassType) {
    return false;
  }

  /**
   * Adds the @Data lombok import and annotation to the class
   *
   * @param topLevelClass
   */
  protected void addAnnotation(TopLevelClass topLevelClass) {
    topLevelClass.addImportedType(ANNOTATION_IMPORTS.get("@Getter"));
    topLevelClass.addAnnotation("@Getter");

    topLevelClass.addImportedType(ANNOTATION_IMPORTS.get("@Setter"));
    topLevelClass.addAnnotation("@Setter");
  }
}
