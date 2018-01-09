#coding:utf-
import sys
import smtplib
from email.mime.text import MIMEText
from email.header import Header

parameter1_emailAddress = sys.argv[1]
parameter2_repositoryName = sys.argv[2]
parameter3_previewUrl = sys.argv[3]

mail_host="smtp.qq.com"
mail_user="1600063551@qq.com"
mail_pass="qoxajqvfwthdbaba"

sender = '1600063551@qq.com'
receivers = [parameter1_emailAddress]

emailMsg = "<center><h2>项目构建成功</h2></center><center><p><a href=\"" + parameter3_previewUrl + "\" style=\"color:red\">点击预览项目</a></p></center>"
message = MIMEText(emailMsg, 'html', 'utf-8')
message['From'] = Header("CI-服务器<1600063551@qq.com>", 'utf-8')
message['To'] =  Header("<" + parameter1_emailAddress + ">", 'utf-8')
subject = "项目" + parameter2_repositoryName + "-任务构建通知"
message['Subject'] = Header(subject, 'utf-8')

try:
    smtpObj = smtplib.SMTP_SSL()
    smtpObj.connect(mail_host, 465)
    smtpObj.login(mail_user,mail_pass)
    smtpObj.sendmail(sender, receivers, message.as_string())
    print "Send Success\n0"
except smtplib.SMTPException:
    print "Error: Send Failed\n401"
