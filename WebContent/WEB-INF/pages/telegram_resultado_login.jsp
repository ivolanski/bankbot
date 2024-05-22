<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<header>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
     >
<link rel="stylesheet" href="../style/style.css" >
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</header>
<body>
	<div class="container-fluid" style="padding: 0px;">
		<div class="row">
			<div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
				<div class="panel panel-default appBackground">
                    <div class="logo">
                        <img src="../images/logo_bradesco.png" />
                        <div class="panel-heading">
						  <h3 class="panel-title">Bradesco Bradeschat</h3>
						  <h6>O banco no seu chat.</h6>
					   </div>
                    </div>
					<div class="panel-body">
                        <div class="alert alert-danger mensagem-alerta" role="alert">
                            <span class="glyphicon glyphicon-exclamation-sign"
                                aria-hidden="true"></span> <span class="sr-only"></span>
                            <span class="mensagemText">${mensagem}</span>
                            <span></span>
                        </div>
						
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
<script>
	    if($('.mensagemText').text()==''){
	        $('.mensagem-alerta').hide();            
	    } 
	    if ($('.mensagemText').text() == 'Login efetuado com sucesso. Clique em fechar esta janela para retornar ao Telegram.') {
	    	window.close();
	    	/* window.addEventListener("load", window.close);*/
        	        
	    }

</script>
    


</html>