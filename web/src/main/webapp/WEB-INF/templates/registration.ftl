<#include "macro_login_registration.ftl"/>

<#macro content>

    <div class="col-lg-4">
    </div>

    <div class="col-lg-8">
        <div class="registration_two_container">

            <h1>Регистрация пользователя</h1>

            <div class="registration_form_data">

                <form action="/registration" method="post" accept-charset="UTF-8">

                    <div class="row">

                        <div class="col-lg-4">

                        <div class="info_enter">
                            <p>Имя</p>
                            <input type="text" name="firstName" placeholder="имя">
                        </div>

                        </div>

                        <div class="col-lg-4">

                            <div class="info_enter">
                                <p>Фамилия</p>
                                <input type="text" name="lastName" placeholder="фамилия">
                            </div>

                        </div>

                        <div class="col-lg-4">

                            <div class="info_enter">
                                <p>Дата рождения</p>
                                <input type="date" name="birthday" placeholder="дата рождения">
                            </div>

                        </div>
                    </div>

                        <div class="row">
                            <div class="col-lg-4">

                                <div class="info_enter">
                                    <p>Email</p>
                                    <input type="text" name="email" placeholder="email">
                                </div>

                            </div>

                            <div class="col-lg-4">

                                <div class="info_enter">
                                    <p>Номер телефона</p>
                                    <input type="text" name="phoneNumber" placeholder="+375-(__)-___-__-__ ">
                                </div>

                            </div>

                            <div class="col-lg-4">

                                <div class="info_enter">
                                    <p>Город</p>
                                    <input type="text" name="city" placeholder="город">
                                </div>

                            </div>
                        </div>


                        <div class="row">
                            <div class="col-lg-4">

                                <div class="info_enter">
                                    <p>Улица</p>
                                    <input type="text" name="street" placeholder="улица">
                                </div>

                            </div>

                            <div class="col-lg-4">

                                <div class="info_enter">
                                    <p>Номер дома</p>
                                    <input type="text" name="homeNumber" placeholder="номер дома">
                                </div>

                            </div>

                            <div class="col-lg-4">

                                <div class="info_enter">
                                    <p>Номер квартиры</p>
                                    <input type="text" name="roomNumber" placeholder="номер квартиры">
                                </div>

                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-4">

                                <div class="info_enter">
                                    <p>Логин</p>
                                    <input type="text" name="login" placeholder="login">
                                </div>

                            </div>

                            <div class="col-lg-4">

                                <div class="info_enter">
                                    <p>Пароль</p>
                                    <input type="password" name="password" placeholder="*********">
                                </div>

                            </div>

                            <div class="col-lg-4">

                                <div class="info_enter">
                                    <p>Повторите пароль</p>
                                    <input type="password" name="repitPassword" placeholder="**********">
                                </div>

                            </div>
                        </div>

                    <div class="row">
                    <div class="col-lg-8">

                    <div class="check_agree">

                        <a href="/rules">Согласен с правилами пользовательского соглашения.</a><br>
                        <label><input name="agree" value="yes" type="checkbox" /> </label>

                    </div>
                    </div>

                        <div class="col-lg-4">

                    <div class="reg_Button">

                        <button class="regButton">Зарегистрироваться</button>

                    </div>

                        </div>



                    </div>

                </form>

            </div>

        </div>
    </div>

</#macro>

<@login_registration title="registration"/>