<#include "macro_reader_room.ftl"/>

<#macro content_2>

 <div class="col-lg-3">

     <div class="reader_profile_data">
         <h3>Данные пользователя</h3><br>
         <p>
             Имя и фамилия: ${user.firstName} ${user.lastName}<br>
             Дата рождения: ${user.birthday}<br>
             Email: ${user.email}<br>
             Номер телефона: ${user.phoneNumber}<br>
             Город: ${user.address.city}<br>
             Улица: ${user.address.street}<br>
             Номер дома: ${user.address.homeNumber}<br>
             Номер квартиры: ${user.address.roomNumber}<br>
             Логин: ${user.login}<br>


         </p>
     </div>

</div>

    <div class="col-lg-7">
        <div class="reader_edit_profile_two_container">

            <h2>Редактирование данных пользователя</h2>

            <div class="registration_form_data">

                <form action="/reader/edit_profile" method="post">

                    <div class="row">

                        <div class="col-lg-4">

                            <div class="info_enter">
                                <p>Имя</p>
                                <input type="text" name="firstName" placeholder=${user.firstName}>
                            </div>

                        </div>

                        <div class="col-lg-4">

                            <div class="info_enter">
                                <p>Фамилия</p>
                                <input type="text" name="lastName" placeholder=${user.lastName}>
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
                                <input type="text" name="email" placeholder=${user.email}>
                            </div>

                        </div>

                        <div class="col-lg-4">

                            <div class="info_enter">
                                <p>Номер телефона</p>
                                <input type="text" name="phoneNumber" placeholder=${user.phoneNumber}>
                            </div>

                        </div>

                        <div class="col-lg-4">

                            <div class="info_enter">
                                <p>Город</p>
                                <input type="text" name="city" placeholder=${user.address.city}>
                            </div>

                        </div>
                    </div>


                    <div class="row">
                        <div class="col-lg-4">

                            <div class="info_enter">
                                <p>Улица</p>
                                <input type="text" name="street" placeholder=${user.address.street}>
                            </div>

                        </div>

                        <div class="col-lg-4">

                            <div class="info_enter">
                                <p>Номер дома</p>
                                <input type="text" name="homeNumber" placeholder=${user.address.homeNumber}>
                            </div>

                        </div>

                        <div class="col-lg-4">

                            <div class="info_enter">
                                <p>Номер квартиры</p>
                                <input type="text" name="roomNumber" placeholder=${user.address.roomNumber}>
                            </div>

                        </div>
                    </div>

                    <div class="row">
                        <div class="col-lg-4">

                            <div class="info_enter">
                                <p>Логин</p>
                                <input type="text" name="login" placeholder=${user.login}>
                            </div>

                        </div>

                        <div class="col-lg-4">

                            <div class="info_enter">
                                <p>Новый пароль</p>
                                <input type="password" name="password" placeholder="************">
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

                        <div class="col-lg">

                            <div class="reg_Button">

                                <button class="regButton" name = "readerId" value="${user.ticketNumber}">Редактировать</button>

                            </div>

                        </div>

                    </div>

                </form>

            </div>

        </div>
    </div>


    <div class="col-lg-2">
        <div class="back_button">
            <form action="/reader/room">
                <button class="myButton">Назад</button>
            </form>
        </div>
    </div>


</#macro>


<@reader_room_menu title="reader_edit_profile"/>