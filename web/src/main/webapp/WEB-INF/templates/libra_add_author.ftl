<#include "macro_emplouee_room.ftl"/>

<#macro content_2>

    <#import "macro_libra_menu.ftl" as libra_reff>
    <@libra_reff.libra_menu/>

</#macro>

<#macro content_3>

    <div class="add_book_output">

        <h1>Добавление автора</h1>

        <form action="/libra/add_author" method="post" >

        <div class="info_book">

            <div class="info_enter">
                <p>Имя автора</p>
                <input type="text" name="firstName" placeholder="имя">
            </div>

            <div class="info_enter">
                <p>Фамилия автора</p>
                <input type="text" name="lastName" placeholder="фамилия">
            </div>


        </div>


        <div class="book_discription">

            <p>
                <textarea name="authorDescription" rows="12" placeholder="описание автора..."></textarea>
            </p>

        </div>

        <div class="add_button">

            <button class="addButton">Добавить</button>

        </div>

        </form>

    </div>



</#macro>

<@emplouee_room_menu title = "add author" />