'use strict'

var app = angular.module('user', []);

app.controller('usercontroller', function ($scope, $http) {


    $scope.forcaSenha=0;
    $scope.getInfoUser = function () {
        $http({
            method: 'GET',
            url: '/med4you/users/logged'
        }).then(function (response) {
            $scope.user = response.data;
        });
    };


    $scope.getInfoUser();

    $scope.cancel = function () {
        $scope.getInfoUser();
    };

    $scope.save = function () {
        if ($scope.user.password !== $scope.user.confirmPassword) {
            alert('A senha não coincide com a confirmação de senha');
        } else if (validaSenha()) {
            null;
        } else {
            $http({
                method: 'PUT',
                url: '/med4you/users',
                data: $scope.user
            }).then(function (response) {
                console.log(response);
                alert('Atualizado com sucesso');
            }, function (error) {
                alert('Erro ao Atualizar');
            });
        }
    }


    $scope.getStyle = function () {
        var forca = verificaForca();
        if (forca < 30) {
            return {'background-color': 'red', 'width': forca};
        } else if (forca < 60) {
            return {'background-color': 'yellow', 'width': forca};
        } else if (forca < 85) {
            return {'background-color': 'blue', 'width': forca};
        } else {
            return {'background-color': 'green', 'width': forca};
        }
    }




    function verificaForca() {
        var senha = $scope.user.password;

        var forca = 0;

        var regLetrasMa     = /[A-Z]/;
        var regLetrasMi     = /[a-z]/;
        var regNumero       = /[0-9]/;
        var regEspecial     = /[!@#$%&*?]/;

        var tam         = false;
        var tamM        = false;
        var letrasMa    = false;
        var letrasMi    = false;
        var numero      = false;
        var especial    = false;

        if(senha.length >= 6) tam = true;
        if(senha.length >= 10) tamM = true;
        if(regLetrasMa.exec(senha)) letrasMa = true;
        if(regLetrasMi.exec(senha)) letrasMi = true;
        if(regNumero.exec(senha)) numero = true;
        if(regEspecial.exec(senha)) especial = true;

        if(tam) forca += 10;
        if(tamM) forca += 10;
        if(letrasMa) forca += 10;
        if(letrasMi) forca += 10;
        if(letrasMa && letrasMi) forca += 20;
        if(numero) forca += 20;
        if(especial) forca += 20;

        $scope.forcaSenha = forca;

        //Map#9jop2a
        return forca;
    }

    function validaSenha() {
        var senha = $scope.user.password;
        var regex = /^(?=(?:.*?[A-Z]){3})(?=(?:.*?[0-9]){2})(?=(?:.*?[!@#$%*()_+^&}{:;?.]){1})(?!.*\s)[0-9a-zA-Z!@#$%;*(){}_+^&]*$/;

        if (senha.length < 8) {
            alert("A senha deve conter no minímo 8 digitos!");
            return false;
        }
        else if (!regex.exec(senha)) {
            alert("A senha deve conter no mínimo 1 caractere maiúsculo, 1 número e 1 caractere especial!");
            return false;
        }
        return true;
    }


});