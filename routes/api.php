<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\Api\UserController;
use App\Http\Controllers\Api\PemiluController;
use App\Http\Controllers\Api\KandidatController;
/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});

Route::post('login', [UserController::Class, 'login'])->name('login');
Route::post('semuapemilu', [PemiluController::Class, 'semuapemilu'])->name('semuapemilu');
Route::post('semuakandidat', [KandidatController::Class, 'semuakandidat'])->name('semuakandidat');
Route::post('votekandidat', [KandidatController::Class, 'votekandidat'])->name('votekandidat');
Route::post('statkandidat', [KandidatController::Class, 'statkandidat'])->name('statkandidat');
