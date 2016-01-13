'use strict';

/* Services */

var services = angular.module('com.gdtrombetti.services', ['ngResource']);

services.factory('UserFactory', function ($resource) {
    return $resource('/testWebApp/REST/users', {}, {
        query: {
            method: 'GET',
            params: {},
            isArray: false
        }
    })
});
