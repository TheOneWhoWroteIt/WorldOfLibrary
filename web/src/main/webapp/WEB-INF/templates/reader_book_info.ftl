<#include "macro_reader_room.ftl"/>

<#macro content_2>
    <div class="col-lg-2">
    </div>

    <div class="col-lg-3">
        <div class="book_info_cover" >
            <img src="${book.imagePath}">
        </div>
    </div>

    <div class="col-lg-4">

        <div class="row" style="height: 40%">
            <div class="col-lg">
                <div class="book_info_description">


                        <div class="book_info_authorname">

                                <p><b>Автор: </b></p>
                            <#list book.authors as author>
                                <form action="/reader/author_info" method="get" style="display:inline!important; margin: 0 0 0 50px">
                                    <button class="authornameButton" name="authorId" value="${author.id}">${author.firstName} ${author.lastName}</button>
                                </form>
                            </#list>

                        </div>
                    <span>
                        <br>
                        <b>Название:</b> ${book.title} <br>
                        <b>Жанр:</b> ${book.genre} <br>
                        <b>Издательство:</b> ${book.publishedName}<br>
                        <b>Год издания:</b> ${book.yearOfPublishing}<br>
                        <b>Страниц:</b> ${book.pageCount}<br>
                        <b>ISBN:</b> ${book.ISBN}<br>

                    </span>

                </div>
            </div>
        </div>

        <div class="row" style="height: 60%">
            <div class="col-lg">
                <div class="book_info_text">
                    <p>
                        ${book.bookDescription}
                    </p>
                </div>
            </div>
        </div>

    </div>

    <div class="col-lg-3" style="align-content: center;">
        <div class="button_menu">

            <#if user.requestFromReader??>
                <#if (user.requestFromReader.order?? && user.requestFromReader.order.orderExpired = false) || !user.requestFromReader.order??>
                    <p style="color: red; font-size: 20px; font-family: 'Lobster', cursive">У вас есть текущая заявка/заказ</p>
                <#elseif user.blackList = true>
                    <p style="color: red; font-size: 20px; font-family: 'Lobster', cursive">Вы в черном списке</p>
                <#else >
                    <form action="/reader/request_add_book" method="post">
                        <button class="myButton" name="bookId" value="${book.id}">Добавить в заявку</button>
                    </form><br>
                </#if>
            <#elseif !user.requestFromReader??>
                       <form action="/reader/request_add_book" method="post">
                            <button class="myButton" name="bookId" value="${book.id}">Добавить в заявку</button>
                       </form><br>
            </#if>

            <form action="/reader/room">
                <button class="myButton">На главную</button>
            </form>

        </div>
    </div>
</#macro>

<@reader_room_menu title="book info"/>