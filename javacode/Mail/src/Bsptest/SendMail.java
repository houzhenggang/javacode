package Bsptest;

import org.apache.commons.mail.EmailAttachment;  
import org.apache.commons.mail.EmailException;  
import org.apache.commons.mail.MultiPartEmail;  

/* 
 * 使用Apache Commons-email组件发送邮件，简单极了
*http://blog.chinaunix.net/uid-20384806-id-1954079.html
*/
  
public class SendMail {  
  
    public static void main(String[] args) throws EmailException, InterruptedException {  
        MultiPartEmail email = new MultiPartEmail();  

        for (int i=1;i<100;i++){
        	email.setTLS(true);  
            email.setHostName("smtp.qq.com"); // 设定smtp服务器  
            email.setAuthentication("603827353@qq.com", "xjh0328xjh"); // 用户名和密码  
      
            /*EmailAttachment attachment = new EmailAttachment();  
            attachment.setPath("C:\\Users\\Administrator\\Desktop\\1.txt");  
            attachment.setDisposition(EmailAttachment.ATTACHMENT);// 设定附件的方式（内嵌，附件）  
            attachment.setDescription("result");  
            attachment.setName("result.txt"); // 附件的文件名  
*/      
            email.addTo("xuejinghao123@126.COM"); // 接收方  
            email.setFrom("603827353@qq.com"); // 发送方  
            email.setSubject("Java Mail Test"); // 标题  
            email.setMsg("test"+i); // 内容  

            /*email.attach(attachment); */ 
            email.send();  
            System.out.println("xjh");
            Thread.sleep(180000);
        }
        
    }  
}  