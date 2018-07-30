'use strict'

var app = angular.module('medicineregister', []);

app.controller('medicineregistercontroller', function ($scope, $http, $state) {

    $scope.image_path = '';

    $scope.clear = function () {
        $scope.medicine = null;
    }
    $scope.save = function () {
        $http({
            method: 'POST',
            url: '/med4you/medicine',
            data: $scope.medicine
        }).then(function (response) {
            console.log(response);
            alert('Medicamento cadastrado com sucesso');
            $state.go("medicine");
        }, function (error) {
            console.log(error);
        });

    }


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