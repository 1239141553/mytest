app.controller('SpecificationController',function($scope,$controller,SpecificationService){
	
	//继承时必须要在HTML中引用
	$controller('baseController',{$scope:$scope});//继承
	
	$scope.save = function(){	
		var serviceObj = null;
		if($scope.entity.specification.id!=null){
			serviceObj = SpecificationService.updateSpecification($scope.entity);
		}else{
			serviceObj = SpecificationService.addSpecification($scope.entity);
		}
		serviceObj.success(function(response){
			if(response.code==200){
				$scope.reloadList();
			  }else{	
				alert(response.msg);						
			}
		});
	}
	$scope.selectIds=[];//选中的ID集合 
    //更新复选
    $scope.delManySpecification = function($event, id) {        
        if($event.target.checked){//如果是被选中,则增加到数组
            $scope.selectIds.push( id);            
        }else{
            var idx = $scope.selectIds.indexOf(id);
            $scope.selectIds.splice(idx, 1);//删除 
        }
    }
	$scope.querySpecificationById = function(specification_id){	
		SpecificationService.querySpecificationById(specification_id).success(function(response){
			$scope.entity=response;
		});
	}
		
	$scope.dele=function(){	
		if(confirm("是否确认删除")){
		SpecificationService.dele($scope.selectId).success(function(response){
			if(response.code==200){ 
				$scope.reloadList();
			}else{                  
				alert(response.msg);
           }                       
		});
	  }
	}
	
	$scope.search = function(pageNum,pageSize){
		SpecificationService.querySpecificationByPage(pageNum,pageSize).success(function(response){
			$scope.specificationList=response.rows;
			$scope.paginationConf.totalItems=response.total;
		});	
	}
	
	$scope.querySpecificationLike = function(){
		$scope.reloadList();
	}
	
	$scope.addTableRows=function(){	
		$scope.entity.specificationOptions.push({});
	}
	
	$scope.deleteTableRows=function(index){		
		$scope.entity.specificationOptions.splice(index,1);
	}
	
});