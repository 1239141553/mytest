app.controller('BrandController',function($scope,$controller,BrandService){
	
	//继承时必须要在HTML中引用
	$controller('baseController',{$scope:$scope});//继承
	
	$scope.save = function(){	
		var serviceObj = null;
		if($scope.entity.id!=null){
			serviceObj = BrandService.updateBrand($scope.entity);
		}else{
			serviceObj = BrandService.addBrand($scope.entity);
		}
		serviceObj.success(function(response){
			if(response.code==200){
				$scope.reloadList();
			  }else{	
				alert(response.msg);						
			}
		});
	}
	
  
	$scope.queryBrandById = function(brand_id){	
		BrandService.queryBrandById(brand_id).success(function(response){
			$scope.entity=response;
		});
	}
	
		
	$scope.dele=function(){	
		if(confirm("是否确认删除")){
		BrandService.dele($scope.selectId).success(function(response){
			if(response.code==200){ 
				$scope.reloadList();
			}else{                  
				alert(response.msg);
           }                       
		});
	  }
	}
	
	$scope.search = function(pageNum,pageSize){
		BrandService.queryBrandByPage(pageNum,pageSize).success(function(response){
			$scope.brandList=response.rows;
			$scope.paginationConf.totalItems=response.total;
		});	
	}

});