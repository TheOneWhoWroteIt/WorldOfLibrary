<#include "macro_emplouee_room.ftl"/>

<#macro content_2>

    <#import "macro_libra_menu.ftl" as libra_ref>
    <@libra_ref.libra_menu/>

</#macro>

<#macro content_3>

    <div class="libra_reader_info">

        <div class="title">
            <h2>Информация о читателе</h2>
        </div>

        <div class="reader_info">
            <p>
                <b>Имя и фамилия:</b> ${reader.firstName} ${reader.lastName}<br>
                <b>Читательский билет:</b> № ${reader.ticketNumber}<br>
                <b>Email:</b> ${reader.email}<br>
                <b>Номер телефона:</b> ${reader.phoneNumber}<br>
                <b>Адресс:</b> город - ${reader.address.city}, улица - ${reader.address.street}, номер дома - ${reader.address.homeNumber}, номер квартиры - ${reader.address.roomNumber}<br><br>
                <b>Черный список:</b> <#if reader.blackList == false> <p>НЕТ</p><#else>ДА</#if><br>
            </p>
        </div>

        <div class="order_or_request_have">

            <div class="reader_order_info">
                <p><b>Открытый заказ: </b></p>
                <#if reader.requestFromReader??>
                    <#if reader.requestFromReader.order?? && reader.requestFromReader.order.orderExpired = false>
                        <form action="/libra/order_info_and_close" method="get" >
                            <button class="authornameButton" name="orderId" value="${reader.requestFromReader.order.orderNumber}" style="font-size: 24px">№${reader.requestFromReader.order.orderNumber}</button>
                        </form>
                    <#else> <p> - </p>
                    </#if>
                <#else> <p> - </p>
                </#if>
            </div>

            <div class="reader_request_info">
                <p><b>Наличие заявки: </b></p>
                <#if reader.requestFromReader?? && reader.requestFromReader.order??>
                    <p> - </p>
                <#elseif reader.requestFromReader?? && !reader.requestFromReader.order??>
                    <form action="/libra/request_from_reader" method="get" >
                        <button class="authornameButton" name="requestId" value="${reader.requestFromReader.id}" style="font-size: 24px">№${reader.requestFromReader.id}</button>
                    </form>
                <#else ><p> - </p>
                </#if>
            </div>
        </div>
    </div>

</#macro>

<@emplouee_room_menu title = "reader info" />