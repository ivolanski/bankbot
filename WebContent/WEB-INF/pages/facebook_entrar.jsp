<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="br.com.teste.servicos.Constantes"
	
	%>

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
                                aria-hidden="true"></span> <span class="sr-only">Atenção:</span>
                            <span class="mensagemText">${mensagem}</span>
                            <span></span>
                        </div>
						<form class="loginForm" role="form" action="entrar" method="post" name="formulario">
							<fieldset>
								<div class="form-group">
									<p class="input_title">Agência</p>
									<input class="form-control" placeholder="1234" id="agencia" name="agencia"
										type="number" autofocus required>
								</div>
								<div class="form-group">
									<p class="input_title">Conta e Dígito sem Hífen</p>
									<input class="form-control" placeholder="123456" id="conta" name="conta"
										type="number" autofocus required>
								</div>
								<div class="form-group dropdown ddnTitular">
									<p class="input_title">Titularidade</p>
									<button class="btn btn-default btn-block dropdown-toggle"
										type="button" data-toggle="dropdown">
										1° Titular <span class="caret"></span>
									</button>
									<ul class="dropdown-menu" name="titularidade">
										<li onclick="$('#titularid').val('1');">1° Titular</li>
										<li onclick="$('#titularid').val('2');">2° Titular</li>
										<li onclick="$('#titularid').val('3');">3° Titular</li>
									</ul>
								</div>
								<div class="form-group">
									<p class="input_title">Token</p>
									<input class="form-control" name="token" placeholder="123456" id="token" name="token"
										type="number" value="" maxlength="6" required>
								</div>

								<div class="form-group">
									<p class="input_title">Senha de 4 dígitos</p>
									<input class="form-control" name="senha" placeholder="****" name="password"
										type="password" value="" maxlength="4" required>
								</div>

								<!-- Change this to a button or input when using this as a form -->
								<div class="form-group checkbox">
									<p class="termosUso"> Ao prosseguir você aceita a <a
										href="${Constantes.URL_SERVIDOR}${Constantes.SERVLET_FACEBOOK_PRIVACIDADE}"
										class="">política de privacidade e termos de uso.</a>
										</p>
									
								</div>
								<input type="hidden" name="bradeschat_auth_token" value="${bradeschat_auth_token}">
								<input type="hidden" name="titular" id="titularid" value="1" >
                                <input type="button" class="btn btn-danger btn-block btnRedBra" style="color:#FFF;border-color:#99000F;background-color:#E60935;font-weight:700;border-radius:2px;border-width:1px;border-style:solid;box-shadow:0 1PX 2PX 0 RGBA(0,0,0,0.5)"
									href="${Constantes.URL_SERVIDOR}${Constantes.SERVLET_FACEBOOK_RESULTADO_LOGIN}" value="Acessar" onclick="validarCampos();">

							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>

<script>
	$(".dropdown-menu li").click(
			function() {
				$(this).parents(".dropdown").find('.btn').html(
				$(this).text() + ' <span class="caret"></span>');
				$(this).parents(".dropdown").find('.btn').val(
						$(this).data('value'));
				 
			});


	    if($('.mensagemText').text()==''){
	        $('.mensagem-alerta').hide();            
	    } 
        if($('.mensagemText').text()=='Login efetuado com sucesso. Clique em fechar esta janela para retornar ao Messenger.'){

        
        	
	        /*window.close();*/
        	window.addEventListener("load", window.close);
        	        
	    }

    function validarCampos(){
       var tamanho_agencia = document.getElementById('agencia').value.length;
        var tamanho_conta = document.getElementById('conta').value.length;
        var tamanho_token = document.getElementById('token').value.length;
        if(tamanho_agencia !=4 ){
            alert("Agencia inválida.");
            document.getElementById('agencia').focus;
            event.preventDefault();
            

        }  else if(tamanho_conta !=6){    
            alert("Conta inválida");
            document.getElementById('conta').focus;
            event.preventDefault();
           

        }  else if(tamanho_token !=6){      
            alert("Token inválido");
            document.getElementById('token').focus;
            event.preventDefault();
            

        }  else{
            document.forms['formulario'].submit();
        }

        
    }
</script>
    


</html>