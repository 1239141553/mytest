app.service('BrandService',function($http){
	var query_url= "http://localhost:8888/shop-query/";
	var edit_url= "http://localhost:8888/shop-edit/";
	
	this.addBrand = function(Brand_entity){
		return $http.post(edit_url+'addBrand',Brand_entity);
	}
	
	this.updateBrand = function(Brand_entity){
		return $http.post(query_url+'updateBrand',Brand_entity);
	}
	
	this.queryBrandById = function(Brand_id){
		return $http.get(query_url+'queryBrandById?id='+Brand_id);
	}
	
	
	this.dele = function(ids){
		return $http.post(edit_url+"delBrand",ids);									
	}
	
	this.queryBrandByPage = function(pageNum,pageSize){
		return $http.get(query_url+"queryBrandByPage?pageNum="+pageNum+"&pageSize="+pageSize);									
	}
	
	this.selectBrandOptionList = function(){
		return $http.get(query_url+'selectBrandOptionList');
	}
	
});