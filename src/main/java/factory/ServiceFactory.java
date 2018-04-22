/*
 * Copyright (c) 2017-2018 
 *
 * by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com) & DAW students
 * 
 * GESANE: Free Open Source Health Management System
 *
 * Sources at:
 *                            https://github.com/rafaelaznar/gesane-server
 *                            https://github.com/rafaelaznar/gesane-client
 *                            https://github.com/rafaelaznar/gesane-database
 *
 * GESANE is distributed under the MIT License (MIT)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package factory;

import bean.helper.ReplyBeanHelper;
import helper.EncodingHelper;
import service.specificimplementation.TipousuarioSpecificServiceImplementation;
import service.specificimplementation.UsuarioSpecificServiceImplementation;
import javax.servlet.http.HttpServletRequest;
import service.specificimplementation.CarritoSpecificServiceImplementation;
import service.specificimplementation.ExtrasSpecificServiceImplementation;
import service.specificimplementation.LineapedidoSpecificServiceImplementation;
import service.specificimplementation.PedidoSpecificServiceImplementation;
import service.specificimplementation.PlatoSpecificServiceImplementation;
import service.specificimplementation.TicketSpecificServiceImplementation;
import service.specificimplementation.TiendaSpecificServiceImplementation;
import service.specificimplementation.TipoplatoSpecificServiceImplementation;

public class ServiceFactory {

    public static ReplyBeanHelper executeMethodService(HttpServletRequest oRequest) throws Exception {
        String ob = oRequest.getParameter("ob");
        String op = oRequest.getParameter("op");
        ReplyBeanHelper oReplyBean = null;
        switch (ob) {
            case "usuario":
                UsuarioSpecificServiceImplementation oUsuarioService = new UsuarioSpecificServiceImplementation(oRequest);
                switch (op) {

                    case "getallobjectsmetadata":
                        oReplyBean = oUsuarioService.getallobjectsmetadata();
                        break;
                    case "getmetadata":
                        oReplyBean = oUsuarioService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oUsuarioService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oUsuarioService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oUsuarioService.get();
                        break;
                    case "set":
                        oReplyBean = oUsuarioService.set();
                        break;
                    case "remove":
                        oReplyBean = oUsuarioService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oUsuarioService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oUsuarioService.getCount();
                        break;
                    case "login":
                        oReplyBean = oUsuarioService.login();
                        break;
                    case "logout":
                        oReplyBean = oUsuarioService.logout();
                        break;
                    case "getsessionstatus":
                        oReplyBean = oUsuarioService.getSessionStatus();
                        break;
                    case "getcountx":
                        oReplyBean = oUsuarioService.getCountX();
                        break;
                    case "getpagex":
                        oReplyBean = oUsuarioService.getPageX();
                        break;
                    case "setpass":
                        oReplyBean = oUsuarioService.setPass();
                        break;
                    case "getsessionuserlevel":
                        oReplyBean = oUsuarioService.getSessionUserLevel();
                        break;
                    case "checklogin":
                        oReplyBean = oUsuarioService.checklogin();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;

            case "tipousuario":
                TipousuarioSpecificServiceImplementation oTipousuarioService = new TipousuarioSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oTipousuarioService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oTipousuarioService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oTipousuarioService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oTipousuarioService.get();
                        break;
                    case "set":
                        oReplyBean = oTipousuarioService.set();
                        break;
                    case "remove":
                        oReplyBean = oTipousuarioService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oTipousuarioService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oTipousuarioService.getCount();
                        break;
                    case "getcountx":
                        oReplyBean = oTipousuarioService.getCountX();
                        break;
                    case "getpagex":
                        oReplyBean = oTipousuarioService.getPageX();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;

            case "tienda":
                TiendaSpecificServiceImplementation oTiendaService = new TiendaSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oTiendaService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oTiendaService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oTiendaService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oTiendaService.get();
                        break;
                    case "set":
                        oReplyBean = oTiendaService.set();
                        break;
                    case "remove":
                        oReplyBean = oTiendaService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oTiendaService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oTiendaService.getCount();
                        break;
                    case "getcountx":
                        oReplyBean = oTiendaService.getCountX();
                        break;
                    case "getpagex":
                        oReplyBean = oTiendaService.getPageX();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;

            case "ticket":
                TicketSpecificServiceImplementation oTicketService = new TicketSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oTicketService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oTicketService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oTicketService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oTicketService.get();
                        break;
                    case "set":
                        oReplyBean = oTicketService.set();
                        break;
                    case "remove":
                        oReplyBean = oTicketService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oTicketService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oTicketService.getCount();
                        break;
                    case "getcountx":
                        oReplyBean = oTicketService.getCountX();
                        break;
                    case "getpagex":
                        oReplyBean = oTicketService.getPageX();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;

            case "plato":
                PlatoSpecificServiceImplementation oPlatoService = new PlatoSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oPlatoService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oPlatoService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oPlatoService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oPlatoService.get();
                        break;
                    case "set":
                        oReplyBean = oPlatoService.set();
                        break;
                    case "remove":
                        oReplyBean = oPlatoService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oPlatoService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oPlatoService.getCount();
                        break;
                    case "getcountx":
                        oReplyBean = oPlatoService.getCountX();
                        break;
                    case "getpagex":
                        oReplyBean = oPlatoService.getPageX();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;

            case "tipoplato":
                TipoplatoSpecificServiceImplementation oTipoplatoService = new TipoplatoSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oTipoplatoService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oTipoplatoService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oTipoplatoService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oTipoplatoService.get();
                        break;
                    case "set":
                        oReplyBean = oTipoplatoService.set();
                        break;
                    case "remove":
                        oReplyBean = oTipoplatoService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oTipoplatoService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oTipoplatoService.getCount();
                        break;
                    case "getcountx":
                        oReplyBean = oTipoplatoService.getCountX();
                        break;
                    case "getpagex":
                        oReplyBean = oTipoplatoService.getPageX();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;

            case "pedido":
                PedidoSpecificServiceImplementation oPedidoService = new PedidoSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oPedidoService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oPedidoService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oPedidoService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oPedidoService.get();
                        break;
                    case "set":
                        oReplyBean = oPedidoService.set();
                        break;
                    case "remove":
                        oReplyBean = oPedidoService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oPedidoService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oPedidoService.getCount();
                        break;
                    case "getcountx":
                        oReplyBean = oPedidoService.getCountX();
                        break;
                    case "getpagex":
                        oReplyBean = oPedidoService.getPageX();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;

            case "lineapedido":
                LineapedidoSpecificServiceImplementation oLineapedidoService = new LineapedidoSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oLineapedidoService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oLineapedidoService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oLineapedidoService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oLineapedidoService.get();
                        break;
                    case "set":
                        oReplyBean = oLineapedidoService.set();
                        break;
                    case "remove":
                        oReplyBean = oLineapedidoService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oLineapedidoService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oLineapedidoService.getCount();
                        break;
                    case "getcountx":
                        oReplyBean = oLineapedidoService.getCountX();
                        break;
                    case "getpagex":
                        oReplyBean = oLineapedidoService.getPageX();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;

            case "extras":
                ExtrasSpecificServiceImplementation oExtrasService = new ExtrasSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oExtrasService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oExtrasService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oExtrasService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oExtrasService.get();
                        break;
                    case "set":
                        oReplyBean = oExtrasService.set();
                        break;
                    case "remove":
                        oReplyBean = oExtrasService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oExtrasService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oExtrasService.getCount();
                        break;
                    case "getcountx":
                        oReplyBean = oExtrasService.getCountX();
                        break;
                    case "getpagex":
                        oReplyBean = oExtrasService.getPageX();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;

            case "carrito":
                CarritoSpecificServiceImplementation oCarritoService = new CarritoSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oCarritoService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oCarritoService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oCarritoService.getPropertiesMetaData();
                        break;
                    case "add":
                        oReplyBean = oCarritoService.add();
                        break;
                    case "removeplato":
                        oReplyBean = oCarritoService.remove();
                        break;
                    case "list":
                        oReplyBean = oCarritoService.list();
                        break;
                    case "buy":
                        oReplyBean = oCarritoService.buy();
                        break;
                    case "empty":
                        oReplyBean = oCarritoService.empty();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;

            default:
                oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Object not found : Please contact your administrator"));
                break;
        }
        return oReplyBean;
    }
}
