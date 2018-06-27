/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oreilly;

/**
 *
 * @author jared.oreilly
 */
public class Main
{
    public static void main(String[] args)
    {
        Manager m = new Manager("http://tagsuatappservice.azurewebsites.net");
        int firstID = m.addNode("GET Login Page", "/Account/LogOn?ReturnUrl=%2F", "GET", "__utmz=11871793.1529959708.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); ASP.NET_SessionId=yxbkku01atz2nl2doeukxixi; __utmc=11871793; ARRAffinity=41ce5e8c7edc575a4867d48b8350edf5cba1a001776701066d01072a2145ff67; __utma=11871793.2114756954.1529959708.1530102653.1530111937.10; __utmb=11871793.4.10.1530111937");
        int secondID = m.addNode("POST Login Details", "/Account/LogOn?ReturnUrl=%2F", "POST", "__utmz=11871793.1529959708.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); ASP.NET_SessionId=yxbkku01atz2nl2doeukxixi; __utmc=11871793; ARRAffinity=41ce5e8c7edc575a4867d48b8350edf5cba1a001776701066d01072a2145ff67; __utma=11871793.2114756954.1529959708.1530102653.1530111937.10; __utmt=1; __utmb=11871793.5.10.1530111937", null, "             UserName: 'user13'\n" +"             Password: 'sgat900'");
        int thirdID = m.addNode("GET Index/Dashboard", "/User/Index", "GET", "__utmz=11871793.1529959708.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); ASP.NET_SessionId=yxbkku01atz2nl2doeukxixi; __utmc=11871793; ARRAffinity=41ce5e8c7edc575a4867d48b8350edf5cba1a001776701066d01072a2145ff67; __utma=11871793.2114756954.1529959708.1530102653.1530111937.10; __utmt=1; __utmb=11871793.5.10.1530111937; .ASPXAUTH=495D2CCD1B504BCB30173C1D8146A821798F0935F051C44BF44BD4C0F6AA1247FB14AB52913AE23042F4C021CBB1AE29C70781874B8BAC0A9ECECC280B91A7D9841C63E9138AB1688AF5B9C7B2C12C62A1F6883E56C227DD4F02E2595B7D97131FBB1F3CF989E3DD000D830D627719548A2CDD20");
        int fourthID = m.addNode("GET LogOff Page", "/Account/LogOff", "GET", "__utmz=11871793.1529959708.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); ASP.NET_SessionId=yxbkku01atz2nl2doeukxixi; __utmc=11871793; ARRAffinity=41ce5e8c7edc575a4867d48b8350edf5cba1a001776701066d01072a2145ff67; __utma=11871793.2114756954.1529959708.1530102653.1530111937.10; __utmt=1; __utmb=11871793.5.10.1530111937; .ASPXAUTH=495D2CCD1B504BCB30173C1D8146A821798F0935F051C44BF44BD4C0F6AA1247FB14AB52913AE23042F4C021CBB1AE29C70781874B8BAC0A9ECECC280B91A7D9841C63E9138AB1688AF5B9C7B2C12C62A1F6883E56C227DD4F02E2595B7D97131FBB1F3CF989E3DD000D830D627719548A2CDD20");
        int edge12 = m.addEdge("Login Pressed", firstID, secondID, 1);
        int edge23 = m.addEdge("Login Succeeded", secondID, thirdID, 0.5);
        int edge34 = m.addEdge("Logs Off", secondID, fourthID, 0.5);
        //System.out.println(m);
        
        System.out.println(m.mayanArtillery());
    }
}
