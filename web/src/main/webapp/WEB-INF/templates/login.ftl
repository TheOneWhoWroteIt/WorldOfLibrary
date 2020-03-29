<#include "macro_login_registration.ftl"/>

<#macro content>
    <div class="col-lg-4">
    </div>

    <div class="col-lg-5">
        <div class="text_log">
            <p>"О мой винтажный читатель! Бумажный букинист, пережиток пыльных чердаков, отважный токсикоман, подсевший на самый опасный в мире наркотик! О доблестный хранитель заплесневелых фолиантов, божественный литературный аутист, спаситель разума от забвения, молю тебя, не исцеляйся!"</p><br>
            <h3>- Фредерик Бегбедер «Конец света. Первые итоги»</h3>
        </div>
    </div>

    <div class="col-lg-3">
        <form action="/login" method="post">

            <div class="login_two_container">

                <h1>Авторизация пользователя</h1>

                <p><#if error??>Not found user</#if></p>


                <div class="login_enter">
                    <p>Введите логин</p>
                    <input type="text" name="username" placeholder="login">
                </div>

                <div class="password_enter">
                    <p>Введите пароль</p>
                    <input type="password" name="password" placeholder="**************">
                </div>

                <div class="in_Button">

                        <button class="inButton">Войти</button>

                </div>

            </div>

        </form>





    </div>

</#macro>

<@login_registration title = "login"/>