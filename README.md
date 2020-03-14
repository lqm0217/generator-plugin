## Custom Mybitis Generator Plugins

### LombokPlusPlugin
* function
add @Getter @Setter for model

* usage
```
    <plugin type="com.mybatis.generator.plugins.LombokPlusPlugin"/>
```

### RenameJavaMapperPlugins
* function
rename *Mapper.java to *Repository.java

* usage
```
    <plugin type="com.mybatis.generator.plugins.RenameJavaMapperPlugins">
      <property name="searchString" value="Mapper$"/>
      <property name="replaceString" value="Repository"/>
    </plugin>
```

### RenameSqlMapperPlugins
* function
rename *Mapper.xml to *Repository.xml

* usage
```
    <plugin type="com.mybatis.generator.plugins.RenameSqlMapperPlugins">
      <property name="searchString" value="Mapper"/>
      <property name="replaceString" value="Repository"/>
    </plugin>
```

### RepositoryAnnotationPlugin
* function
add @Repository for Repository interface

* usage
```
    <plugin type="com.mybatis.generator.plugins.RepositoryAnnotationPlugin">
      <property name="enableRepository" value="true"/>
    </plugin>
```

### RenameJavaModelPlugins
* function
rename model class to *Entity.java

* usage
```
    <plugin type="com.mybatis.generator.plugins.RenameJavaModelPlugins">
      <property name="suffixString" value="entity"/>
    </plugin>
```