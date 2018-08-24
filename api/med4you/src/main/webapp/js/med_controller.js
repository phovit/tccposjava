'use strict'

var app = angular.module('medicine', []);

app.controller('medicinecontroller', function ($scope, $http) {


    $scope.listarMedicamentos = function () {
        $http({
            method: 'GET',
            url: '/med4you/medicine'
        }).then(function (response) {
            $scope.medicines = response.data;
        }, function (error) {
            console.log(error);
        });
    };
    $scope.save = function (object) {
        $http({
            method: 'POST',
            url: '/med4you/medicine',
            data: object
        }).then(function (response) {
            console.log(response);
        }, function (error) {
            console.log(error);
        });

    }

    var loadDatabase = [
        {   "name":"NomeMedicamento",
            "codebar":"999999999",
            "msRecord":"000000000",
            "generic":true,
            "indication":"Medicamento indicado para febre",
            "contraindication":"Medicamento contra indicado em caso de suspeita de dengue",
            "adverseReactions":"possiveis náuseas", "precautions":"não ministrar dose maior do que 2 cápsulas a cada 6 horas",
            "image":"iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg=="
        },
        {   "name":"NomeMedicamento",
            "codebar":"999999999",
            "msRecord":"000000000",
            "generic":true,
            "indication":"Medicamento indicado para febre",
            "contraindication":"Medicamento contra indicado em caso de suspeita de dengue",
            "adverseReactions":"possiveis náuseas", "precautions":"não ministrar dose maior do que 2 cápsulas a cada 6 horas",
            "image":"iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg=="
        },
        {   "name":"NomeMedicamento",
            "codebar":"999999999",
            "msRecord":"000000000",
            "generic":true,
            "indication":"Medicamento indicado para febre",
            "contraindication":"Medicamento contra indicado em caso de suspeita de dengue",
            "adverseReactions":"possiveis náuseas", "precautions":"não ministrar dose maior do que 2 cápsulas a cada 6 horas",
            "image":"iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg=="
        },
        {   "name":"NomeMedicamento",
            "codebar":"999999999",
            "msRecord":"000000000",
            "generic":true,
            "indication":"Medicamento indicado para febre",
            "contraindication":"Medicamento contra indicado em caso de suspeita de dengue",
            "adverseReactions":"possiveis náuseas", "precautions":"não ministrar dose maior do que 2 cápsulas a cada 6 horas",
            "image":"iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg=="
        },
        {   "name":"NomeMedicamento",
            "codebar":"999999999",
            "msRecord":"000000000",
            "generic":true,
            "indication":"Medicamento indicado para febre",
            "contraindication":"Medicamento contra indicado em caso de suspeita de dengue",
            "adverseReactions":"possiveis náuseas", "precautions":"não ministrar dose maior do que 2 cápsulas a cada 6 horas",
            "image":"iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg=="
        },
        {   "name":"NomeMedicamento",
            "codebar":"999999999",
            "msRecord":"000000000",
            "generic":true,
            "indication":"Medicamento indicado para febre",
            "contraindication":"Medicamento contra indicado em caso de suspeita de dengue",
            "adverseReactions":"possiveis náuseas", "precautions":"não ministrar dose maior do que 2 cápsulas a cada 6 horas",
            "image":"iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg=="
        },
        {   "name":"NomeMedicamento",
            "codebar":"999999999",
            "msRecord":"000000000",
            "generic":true,
            "indication":"Medicamento indicado para febre",
            "contraindication":"Medicamento contra indicado em caso de suspeita de dengue",
            "adverseReactions":"possiveis náuseas", "precautions":"não ministrar dose maior do que 2 cápsulas a cada 6 horas",
            "image":"iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg=="
        },
];

    for (var i =0 ; i < loadDatabase.length ; i++){
        $scope.save(loadDatabase[i]);
    }


    $scope.listarMedicamentos();

});