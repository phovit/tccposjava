'use strict'

var app = angular.module('user', []);

app.controller('usercontroller', function ($scope, $http) {
    console.log('usercontroller');
    $scope.oldUser={};

    $scope.forcaSenha=0;
    $scope.getInfoUser = function () {
        $http({
            method: 'GET',
            url: '/med4you/users/logged'
        }).then(function (response) {
            $scope.user = response.data;
            $scope.oldUser = angular.copy($scope.user);
        });
    };

    $scope.changedUser = function(){
        return $scope.oldUser.address !== $scope.user.address ||
        $scope.oldUser.birthDate !== $scope.user.birthDate ||
        $scope.oldUser.cellPhone !== $scope.user.cellPhone ||
        $scope.oldUser.cpf !== $scope.user.cpf ||
        $scope.oldUser.email !== $scope.user.email ||
        $scope.oldUser.id !== $scope.user.id ||
        $scope.oldUser.identity !== $scope.user.identity ||
        $scope.oldUser.name !== $scope.user.name ||
        $scope.oldUser.phone !== $scope.user.phone ||
        $scope.oldUser.username !== $scope.user.username
    }

    $scope.getInfoUser();

    $scope.cancel = function () {
        $scope.getInfoUser();
    };

    $scope.save = function () {
        if ($scope.user.password !== $scope.user.confirmPassword && !!$scope.changePassword) {
            alert('A senha não coincide com a confirmação de senha');
        } else if ( !!$scope.changePassword && !validaSenha()) {
            null;
        } else if ( !$scope.changedUser()  ) {
            alert('O usuário não foi alterado');
        } else {
            $scope.sendPhoto();

            $http({
                method: 'PUT',
                url: '/med4you/users',
                data: $scope.user
            }).then(function (response) {
                console.log(response);
                alert('Atualizado com sucesso');
                $scope.getInfoUser();
            }, function (error) {
                alert('Erro ao Atualizar');
                $scope.getInfoUser();
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
        if( !$scope.user || !$scope.user.password ){
            return 0;
        }
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
        if ( !senha ){
            senha = '';
        }
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

    $scope.sendPhoto = function () {
        $http({
            method: 'POST',
            url: '/med4you/files/upload',
            data: $scope.image,
            transformRequest: angular.identity,
            headers: {enctype:'multipart/form-data',
                              'Content-Type': undefined
                    }
        }).then(function (response) {
            console.log(response);
            alert('upload ok -->'+response.data);
        }, function (error) {
            alert('Erro ao enviar imagem');
        });
    };

})
    .service("uploadService", function ($http, $q) {

        return ({
            upload: upload
        });

        function upload(file) {
            var upl = $http({
                method: 'POST',
                url: 'http://jsonplaceholder.typicode.com/posts', // /api/upload
                headers: {
                    'Content-Type': 'multipart/form-data'
                },
                data: {
                    upload: file
                },
                transformRequest: function (data, headersGetter) {
                    var formData = new FormData();
                    angular.forEach(data, function (value, key) {
                        formData.append(key, value);
                    });

                    var headers = headersGetter();
                    delete headers['Content-Type'];

                    return formData;
                }
            });
            return upl.then(handleSuccess, handleError);

        }

        function handleError(response, data) {
            if (!angular.isObject(response.data) || !response.data.message) {
                return ($q.reject("An unknown error occurred."));
            }

            return ($q.reject(response.data.message));
        }

        function handleSuccess(response) {
            return (response);
        }

    })
    .directive("fileinput", [function () {
        return {
            scope: {
                fileinput: "=",
                filepreview: "="
            },
            link: function (scope, element, attributes) {
                element.bind("change", function (changeEvent) {
                    scope.fileinput = changeEvent.target.files[0];
                    var reader = new FileReader();
                    reader.onload = function (loadEvent) {
                        scope.$apply(function () {
                            scope.filepreview = loadEvent.target.result;
                            if(!scope.$parent.medicine){
                                scope.$parent.medicine = {};
                            }
                            scope.$parent.medicine.image = loadEvent.target.result.substring(loadEvent.target.result.indexOf('base64,')+7);
                            angular.element(document.querySelector('#imageField')).css({
                                'background-image': 'url("data:image/png;base64,' + scope.$parent.medicine.image  + '")',
                                'background-repeat': 'no-repeat',
                                'background-size': '100% 100%'
                            });

                        });
                    }
                    reader.readAsDataURL(scope.fileinput);
                });
            }
        }
    }]);