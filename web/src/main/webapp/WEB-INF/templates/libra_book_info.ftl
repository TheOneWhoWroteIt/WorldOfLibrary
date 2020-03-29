<#include "macro_emplouee_room.ftl"/>

<#macro content_2>

    <#import "macro_libra_menu.ftl" as libra_reff>
    <@libra_reff.libra_menu/>

</#macro>

<#macro content_3>

<div class="libra_book_info">

<div class="row" style="height: 8%">
    <div class="col-lg">
        <div class="page_name">
            <p>Информация о книге</p>
        </div>
    </div>
</div>

<div class="row" style="height: 84%">

    <div class="col-lg-5">
        <div class="book_info_cover" >
            <img src="${book.imagePath}" class="book_cover">
        </div>
    </div>

    <div class="col-lg-7">

        <div class="row" style="height: 40%">
            <div class="col-lg">
                <div class="book_info_description">


                    <div class="book_info_authorname">

                        <p><b>Автор: </b></p>
                        <#list book.authors as author>
                        <form action="/libra/author_info" method="post" style="display:inline!important; margin: 0 0 0 50px">
                            <button class="authornameButton" name="authorId" value="${author.id}">${author.firstName} ${author.lastName}</button>
                        </form>
                        </#list>

                    </div>
                    <span>
                        <br>
                        <b>Название:</b> ${book.title} <br>
                        <b>Жанр:</b> ${book.genre} <br>
                        <b>Издательство:</b> ${book.publishedName} <br>
                        <b>Год издания:</b> ${book.yearOfPublishing} <br>
                        <b>Страниц:</b> ${book.pageCount} <br>
                        <b>ISBN:</b> ${book.ISBN} <br><br>

                    </span>

                </div>
            </div>
        </div>

        <div class="row" style="height: 40%; overflow: auto">
            <div class="col-lg">
                <div class="book_info_text">
                    <p>
                        ${book.bookDescription}
                    </p>
                </div>
            </div>
        </div>

    </div>

</div>

<div class="row"style="height: 8%">
    <div class="edit_book_button">
        <form action="/libra/edit_book" method="get">
            <button class="myButton" name="bookId" value="${book.id}">Редактировать</button>
        </form>
    </div>
</div>

</div>



</#macro>

<@emplouee_room_menu title = "book info" />