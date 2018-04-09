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
package service.specificimplementation;

import com.google.gson.Gson;
import bean.helper.MetaBeanHelper;
import bean.helper.ReplyBeanHelper;
import bean.specificimplementation.CarritoSpecificBeanImplementation;
import bean.specificimplementation.LineapedidoSpecificBeanImplementation;
import bean.specificimplementation.PedidoSpecificBeanImplementation;
import bean.specificimplementation.PlatoSpecificBeanImplementation;
import bean.specificimplementation.TicketSpecificBeanImplementation;
import bean.specificimplementation.UsuarioSpecificBeanImplementation;
import connection.publicinterface.ConnectionInterface;
import dao.specificimplementation.LineapedidoSpecificDaoImplementation;
import dao.specificimplementation.PedidoSpecificDaoImplementation;
import dao.specificimplementation.PlatoSpecificDaoImplementation;
import dao.specificimplementation.TicketSpecificDaoImplementation;
import factory.ConnectionFactory;
import helper.constant.ConfigurationConstants;
import helper.constant.ConnectionConstants;
import helper.GsonHelper;
import helper.Log4jHelper;
import helper.RandomHelper;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import service.genericimplementation.TableGenericServiceImplementation;

public class CarritoSpecificServiceImplementation extends TableGenericServiceImplementation {

    public CarritoSpecificServiceImplementation(HttpServletRequest request) {
        super(request);
    }

    private CarritoSpecificBeanImplementation find(Integer id, ArrayList<CarritoSpecificBeanImplementation> alCarrito) {
        if (alCarrito != null) {
            Iterator<CarritoSpecificBeanImplementation> iterator = alCarrito.iterator();
            while (iterator.hasNext()) {
                CarritoSpecificBeanImplementation oCarritoBean = iterator.next();
                PlatoSpecificBeanImplementation oPlato = (PlatoSpecificBeanImplementation) oCarritoBean.getObj_plato().getBean();
                if (oPlato.getId() == id) {
                    return oCarritoBean;
                }
            }
        }
        return null;
    }

    public ReplyBeanHelper add() throws Exception {
        if (this.checkPermission("add")) {
            ArrayList<CarritoSpecificBeanImplementation> alCarrito = (ArrayList) oRequest.getSession().getAttribute("carrito");
            ReplyBeanHelper oReplyBean = null;
            CarritoSpecificBeanImplementation oCarritoBeanEnCarrito = null;
            int id = Integer.parseInt(oRequest.getParameter("id"));
            int cantidad = Integer.parseInt(oRequest.getParameter("cantidad"));
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            if (alCarrito == null) {
                alCarrito = new ArrayList<CarritoSpecificBeanImplementation>();
            }
            oCarritoBeanEnCarrito = find(id, alCarrito);
            if (oCarritoBeanEnCarrito != null) {
                oCarritoBeanEnCarrito.setCantidad(oCarritoBeanEnCarrito.getCantidad() + cantidad);
            } else {
                try {
                    oPooledConnection = ConnectionFactory.getSourceConnection(ConnectionConstants.connectionName);
                    oConnection = oPooledConnection.newConnection();
                    CarritoSpecificBeanImplementation oCarritoBean = new CarritoSpecificBeanImplementation();
                    oCarritoBean.setCantidad(cantidad);
                    PlatoSpecificDaoImplementation oPlatoDao = new PlatoSpecificDaoImplementation(oConnection, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
                    MetaBeanHelper oMetaBeanHelper = (MetaBeanHelper) oPlatoDao.get(id, ConfigurationConstants.jsonMsgDepth);
                    PlatoSpecificBeanImplementation oPlatoBeanAdd = (PlatoSpecificBeanImplementation) oMetaBeanHelper.getBean();
                    oCarritoBean.setId_plato(oPlatoBeanAdd.getId());
                    oCarritoBean.setObj_plato(oMetaBeanHelper);
                    alCarrito.add(oCarritoBean);
                } catch (Exception ex) {
                    String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
                    Log4jHelper.errorLog(msg, ex);
                    throw new Exception(msg, ex);
                } finally {
                    if (oConnection != null) {
                        oConnection.close();
                    }
                    if (oPooledConnection != null) {
                        oPooledConnection.disposeConnection();
                    }
                }
            }
            oRequest.getSession().setAttribute("carrito", alCarrito);
            oReplyBean = new ReplyBeanHelper(200, GsonHelper.getGson().toJson(alCarrito));
            return oReplyBean;
        } else {
            return new ReplyBeanHelper(401, "Unauthorized operation");
        }
    }

    @Override
    public ReplyBeanHelper remove() throws Exception {
        if (this.checkPermission("remove")) {
            ArrayList<CarritoSpecificBeanImplementation> alCarrito = (ArrayList) oRequest.getSession().getAttribute("carrito");
            int id = Integer.parseInt(oRequest.getParameter("id"));
            ReplyBeanHelper oReplyBean = null;
            CarritoSpecificBeanImplementation oCarritoBeanEnCarrito = find(id, alCarrito);
            alCarrito.remove(oCarritoBeanEnCarrito);
            Gson oGson = GsonHelper.getGson();
            String strJson = oGson.toJson(alCarrito);
            oReplyBean = new ReplyBeanHelper(200, strJson);
            return oReplyBean;
        } else {
            return new ReplyBeanHelper(401, "Unauthorized operation");
        }
    }

    public ReplyBeanHelper list() throws Exception {
        if (this.checkPermission("list")) {
            ArrayList<CarritoSpecificBeanImplementation> alCarrito = (ArrayList) oRequest.getSession().getAttribute("carrito");
            ReplyBeanHelper oReplyBean = null;
            Gson oGson = GsonHelper.getGson();
            String strJson = oGson.toJson(alCarrito);
            oReplyBean = new ReplyBeanHelper(200, strJson);
            return oReplyBean;
        } else {
            return new ReplyBeanHelper(401, "Unauthorized operation");
        }
    }

    public ReplyBeanHelper buy() throws Exception {
        if (this.checkPermission("buy")) {
            ArrayList<CarritoSpecificBeanImplementation> alCarrito = (ArrayList) oRequest.getSession().getAttribute("carrito");
            MetaBeanHelper oUsuarioBeanConMetaDatos = (MetaBeanHelper) oRequest.getSession().getAttribute("user");
            ReplyBeanHelper oReplyBean = null;
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            RandomHelper ubicacion = new RandomHelper();
            

            try {
                oPooledConnection = ConnectionFactory.getSourceConnection(ConnectionConstants.connectionName);
                oConnection = oPooledConnection.newConnection();
                if (alCarrito != null && alCarrito.size() > 0) {
                    oConnection.setAutoCommit(false);
                    PedidoSpecificBeanImplementation oPedidoBean = new PedidoSpecificBeanImplementation();
                    oPedidoBean.setId_usuario(((UsuarioSpecificBeanImplementation) oUsuarioBeanConMetaDatos.getBean()).getId());
                    oPedidoBean.setFecha_pedido(Calendar.getInstance().getTime());
                    PedidoSpecificDaoImplementation oPedidoDao = new PedidoSpecificDaoImplementation(oConnection, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
                    oPedidoBean.setId_tienda(((UsuarioSpecificBeanImplementation) oUsuarioBeanConMetaDatos.getBean()).getId_tienda());
                    oPedidoBean.setId(oPedidoDao.set(oPedidoBean));

                    Iterator<CarritoSpecificBeanImplementation> iterator = alCarrito.iterator();
                    while (iterator.hasNext()) {
                        CarritoSpecificBeanImplementation oCarritoBean = iterator.next();
                        MetaBeanHelper oPlatoBeanDeCarritoConMetaDatos = oCarritoBean.getObj_plato();
                        PlatoSpecificBeanImplementation oPlatoBeanDeCarrito = (PlatoSpecificBeanImplementation) oPlatoBeanDeCarritoConMetaDatos.getBean();
                        PlatoSpecificDaoImplementation oPlatoDao = new PlatoSpecificDaoImplementation(oConnection, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
                        MetaBeanHelper oMetaBeanHelper = (MetaBeanHelper) oPlatoDao.get(oPlatoBeanDeCarrito.getId(), ConfigurationConstants.jsonMsgDepth);
                        PlatoSpecificBeanImplementation oPlatoBeanDeDB = (PlatoSpecificBeanImplementation) oMetaBeanHelper.getBean();
                        
                        double importeTotal = 0.0;
                        double importeElemento = 0.0;
                        
                        if (oPlatoBeanDeDB.getExistencias() > oCarritoBean.getCantidad()) {
                            LineapedidoSpecificBeanImplementation oLineadepedidoBean = new LineapedidoSpecificBeanImplementation();
                            TicketSpecificBeanImplementation oTicketdepedidoBean = new TicketSpecificBeanImplementation();

                            importeElemento = oCarritoBean.getCantidad() * oPlatoBeanDeCarrito.getPrecio();
                            importeTotal += importeElemento;
                            
                            oLineadepedidoBean.setCantidad(oCarritoBean.getCantidad());
                            oLineadepedidoBean.setId_pedido(oPedidoBean.getId());
                            oLineadepedidoBean.setId_plato(oPlatoBeanDeCarrito.getId());
                            oLineadepedidoBean.setImporte(importeElemento);

                            oTicketdepedidoBean.setFecha_ticket(oPedidoBean.getFecha_pedido());
                            oTicketdepedidoBean.setId_pedido(oPedidoBean.getId());
                            oTicketdepedidoBean.setDescripcion(oPlatoBeanDeCarrito.getDescripcion());
                            oTicketdepedidoBean.setImporte(importeTotal);
                            oTicketdepedidoBean.setIva(21);
                            
                            LineapedidoSpecificDaoImplementation oLineadepedidoDao = new LineapedidoSpecificDaoImplementation(oConnection, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
                            oLineadepedidoBean.setId(oLineadepedidoDao.set(oLineadepedidoBean));
                            oPlatoBeanDeCarrito.setExistencias(oPlatoBeanDeCarrito.getExistencias() - oCarritoBean.getCantidad());

                            TicketSpecificDaoImplementation oTicketdepedidoDao = new TicketSpecificDaoImplementation(oConnection, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
                            oTicketdepedidoBean.setId(oTicketdepedidoDao.set(oTicketdepedidoBean));
                            
                            oPlatoDao.set(oPlatoBeanDeCarrito);
                        }
                    }
                    alCarrito.clear();
                    oConnection.commit();
                }
            } catch (Exception ex) {
                oConnection.rollback();
                String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
                Log4jHelper.errorLog(msg, ex);
                throw new Exception(msg, ex);
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oPooledConnection != null) {
                    oPooledConnection.disposeConnection();
                }
            }
            return oReplyBean = new ReplyBeanHelper(200, "Compra realizada correctamente");
        } else {
            return new ReplyBeanHelper(401, "Unauthorized operation");
        }
    }

    public ReplyBeanHelper empty() throws Exception {
        if (this.checkPermission("empty")) {
            ArrayList<CarritoSpecificBeanImplementation> alCarrito = (ArrayList) oRequest.getSession().getAttribute("carrito");
            ReplyBeanHelper oReplyBean = null;
            alCarrito.clear();
            Gson oGson = GsonHelper.getGson();
            String strJson = oGson.toJson(alCarrito);
            oReplyBean = new ReplyBeanHelper(200, strJson);
            return oReplyBean;
        } else {
            return new ReplyBeanHelper(401, "Unauthorized operation");
        }
    }
}
