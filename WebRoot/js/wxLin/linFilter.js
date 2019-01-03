 function stateFilter(state){
    	$("table tbody tr").hide().filter(":contains("+state+")").show();
 }