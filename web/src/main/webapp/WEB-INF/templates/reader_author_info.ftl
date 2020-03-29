<#include "macro_reader_room.ftl"/>

<#macro content_2>

    <div class="col-lg-2">
    </div>

    <div class="col-lg-7">
        <div class="author_info_block">
        <h1>${author.firstName} ${author.lastName}</h1>
        <div class="author_info">
            <p>
                ${author.authorDescription}
            </p>
        </div>
        </div>
    </div>

    <div class="col-lg-3">
        <div class="author_menu" >
            <div class="in_root" >
                <form action="/reader/room">
                    <button class="myButton">На главную</button>
                </form>
            </div>

            <div class="author_book_list" >
                <h2>Книги автора</h2>
                <p>
                <#list books as book>
                <form action="/reader/book_info" method="get">
                    <button class="authornameButton" name="bookId" value="${book.id}" style="font-size: 20px">${book.title}</button>
                </form>
                </#list>
                </p>
            </div>
        </div>
    </div>

</#macro>

<@reader_room_menu title="author info"/>