<#include "macro_emplouee_room.ftl"/>

<#macro content_2>

    <#import "macro_libra_menu.ftl" as libra_reff>
    <@libra_reff.libra_menu/>

</#macro>

<#macro content_3>

    <div class="add_book_output">

        <h1>Редактирование данных об авторе</h1>

        <form action="/libra/edit_author" method="post" >

            <div class="info_book">

                <div class="info_enter">
                    <p>Имя автора</p>
                    <input type="text" name="firstName" placeholder="${author.firstName}">
                </div>

                <div class="info_enter">
                    <p>Фамилия автора</p>
                    <input type="text" name="lastName" placeholder="${author.lastName}">
                </div>


            </div>


            <div class="book_discription">

                <p>
                    <textarea name="authorDescription" rows="12" placeholder="${author.authorDescription}"></textarea>
                </p>

            </div>

            <div class="add_button">

                <button class="addButton">Изменить</button>

            </div>

        </form>

    </div>



</#macro>

<@emplouee_room_menu title = "edit author" />