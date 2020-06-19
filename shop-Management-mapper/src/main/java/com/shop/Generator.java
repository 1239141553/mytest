
package com.shop;


import org.apache.ibatis.io.Resources;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Generator {

	public static void createMappers() throws Exception{
		//{1} 这个 List 不用管 ..
		List<String> list = new ArrayList<>();
		InputStream fis = Resources.getResourceAsStream(
				"generatorConfig.xml");
		//{1} 创建配置文件解析器
		ConfigurationParser cp = new ConfigurationParser( list );
		//{2} 解析配置文件
		Configuration config = cp.parseConfiguration( fis );
		//{3} 创建默认回调对象
		DefaultShellCallback callback = new DefaultShellCallback( true );
		//{4} 创建创建器
		MyBatisGenerator myGenerator = new MyBatisGenerator(
				config,
				callback, list );
		//{5} 执行创建
		myGenerator.generate( null );

		System.out.println("+-----------------------+");
		System.out.println("执行反向工程成功....");
		System.out.println("+-----------------------+");

	}

	public static void main(String[] args) throws Exception {
		createMappers();
	}

}