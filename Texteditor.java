import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;



public class Texteditor extends JFrame implements ActionListener
{
    MenuBar mbar;
    Menu file,edit,format,font,font1,font2;
    MenuItem item1,item2,item3,item4;
    MenuItem item5,item6,item7,item8,item9,item10;
    MenuItem fname1,fname2,fname3,fname4;
    MenuItem fstyle1,fstyle2,fstyle3,fstyle4;
    MenuItem fsize1,fsize2,fsize3,fsize4;

    JPanel mainpanel;
    TextArea text;

    Font f;
    String months[]={
            "Январь","Февраль","Март","Апрель",
            "Май","Июнь","Июль","Август",
            "Сентябрь","Октябрь","Ноябрь","Декабрь"};

    GregorianCalendar gcalendar;

    String command=" ";
    String str=" ";

    String str1=" ",str2=" ",str3=" ";
    String str4=" ";

    String str6=" ";
    String str7=" ",str8=" ",str9=" ";

    int len1;

    int i=0;
    int pos1;
    int len;



    public Texteditor(String str)
    {

        super(str);

        mainpanel=new JPanel();
        mainpanel=(JPanel)getContentPane();
        mainpanel.setLayout(new FlowLayout());

        mbar=new MenuBar();
        setMenuBar(mbar);

        file=new Menu("Файл");
        edit=new Menu("Изменить");
        format=new Menu("Стиль");
        font=new Menu("Шрифт");
        font1=new Menu("Формат");
        font2=new Menu("Размер");

        file.add(item1=new MenuItem("Создать"));
        file.add(item2=new MenuItem("Открыть..."));
        file.add(item3=new MenuItem("Сохранить как..."));
        file.add(item4=new MenuItem("Выход"));
        mbar.add(file);

        edit.add(item5=new MenuItem("Вырезать (Ctrl+X)"));
        edit.add(item6=new MenuItem("Копировать (Ctrl+C)"));
        edit.add(item7=new MenuItem("Вставить (Ctrl+V)"));
        edit.add(item8=new MenuItem("Удалить"));
        edit.add(item10=new MenuItem("Выделить все (Ctrl+A)"));
        edit.add(item9=new MenuItem("Дата и время"));
        mbar.add(edit);

        format.add(font);
        format.add(font1);
        format.add(font2);

        font.add(fname1=new MenuItem("Courier"));
        font.add(fname2=new MenuItem("Times New Roman"));

        font1.add(fstyle1=new MenuItem("Обычный"));
        font1.add(fstyle2=new MenuItem("Полужирный"));
        font1.add(fstyle3=new MenuItem("Курсив"));
        font1.add(fstyle4=new MenuItem("Полужирный курсив"));

        font2.add(fsize1=new MenuItem("12"));
        font2.add(fsize2=new MenuItem("14"));
        font2.add(fsize3=new MenuItem("18"));
        font2.add(fsize4=new MenuItem("20"));

        mbar.add(format);

        item1.addActionListener(this);
        item2.addActionListener(this);
        item3.addActionListener(this);
        item4.addActionListener(this);
        item5.addActionListener(this);
        item6.addActionListener(this);
        item7.addActionListener(this);
        item8.addActionListener(this);
        item9.addActionListener(this);
        item10.addActionListener(this);
        fname1.addActionListener(this);
        fname2.addActionListener(this);
        fstyle1.addActionListener(this);
        fstyle2.addActionListener(this);
        fstyle3.addActionListener(this);
        fstyle4.addActionListener(this);
        fsize1.addActionListener(this);
        fsize2.addActionListener(this);
        fsize3.addActionListener(this);
        fsize4.addActionListener(this);

        text=new TextArea(42,150);
        mainpanel.add(text);

        f=new Font("Times New Roman",Font.PLAIN,14);
        text.setFont(f);
    }



    public void actionPerformed(ActionEvent ae)
    {

        command=(String)ae.getActionCommand();

        if(command.equals("Создать"))
        {
            dispose();
            Texteditor note1 = new Texteditor("Untitled-Notepad");
            note1.setSize(1920,1080);
            note1.setVisible(true);
        }

        try
        {

            if(command.equals("Открыть"))
            {

                str4=" ";
                FileDialog dialog=new FileDialog(this,"Открыть");
                dialog.setVisible(true);

                str1=dialog.getDirectory();
                str2=dialog.getFile();
                str3=str1+str2;
                File f=new File(str3);
                FileInputStream fobj=new FileInputStream(f);
                len=(int)f.length();

                for(int j=0;j<len;j++)
                {

                    char str5=(char)fobj.read();
                    str4=str4 + str5;
                }
                text.setText(str4);
            }
        }

        catch(IOException e)
        {}

        try
        {

            if(command.equals("Сохранить как..."))
            {

                FileDialog dialog1=new FileDialog(this,"Сохранить как",FileDialog.SAVE);
                dialog1.setVisible(true);

                str7=dialog1.getDirectory();
                str8=dialog1.getFile();
                str9=str7+str8;


                str6=text.getText();
                len1=str6.length();
                byte buf[]=str6.getBytes();

                File f1=new File(str9);
                FileOutputStream fobj1=new FileOutputStream(f1);

                for(int k=0;k<len1;k++)
                {

                    fobj1.write(buf[k]);
                }
                fobj1.close();
            }
            this.setTitle(str8);
        }

        catch(IOException e){}


        if(command.equals("Выход"))
        {
            System.exit(0);
        }


        if(command.equals("Вырезать (Ctrl+X)"))
        {

            str=text.getSelectedText();
            i=text.getText().indexOf(str);
            text.replaceRange(" ",i,i+str.length());
        }

        if(command.equals("Копировать (Ctrl+C)"))
        {

            str=text.getSelectedText();
        }

        if(command.equals("Вставить (Ctrl+V)"))
        {

            pos1=text.getCaretPosition();
            text.insert(str,pos1);
        }

        if(command.equals("Выделить все (Ctrl+A)"))
        {

            String strText=text.getText();
            int strLen=strText.length();
            text.select(0,strLen);
        }

        if(command.equals("Удалить"))
        {

            String msg=text.getSelectedText();
            i=text.getText().indexOf(msg);
            text.replaceRange(" ",i,i+msg.length());
        }

        if(command.equals("Дата и время"))
        {

            gcalendar=new GregorianCalendar();
            String h=String.valueOf(gcalendar.get(Calendar.HOUR));
            String m=String.valueOf(gcalendar.get(Calendar.MINUTE));
            String s=String.valueOf(gcalendar.get(Calendar.SECOND));
            String date=String.valueOf(gcalendar.get(Calendar.DATE));
            String mon=months[gcalendar.get(Calendar.MONTH)];
            String year=String.valueOf(gcalendar.get(Calendar.YEAR));
            String hms="Время"+" - "+h+":"+m+":"+s+" Дата"+" - "+date+" "+mon+" "+year;
            int loc=text.getCaretPosition();
            text.insert(hms,loc);
        }


        if(command.equals("Courier new"))
        {

            String fontName=f.getName();
            String fontFamily=f.getFamily();
            int fontSize=f.getSize();
            int fontStyle=f.getStyle();

            f=new Font("Courier new",fontStyle,fontSize);
            text.setFont(f);
        }

        if(command.equals("Times New Roman"))
        {

            String fontName=f.getName();
            String fontFamily=f.getFamily();
            int fontSize=f.getSize();
            int fontStyle=f.getStyle();

            f=new Font("Times New Roman",fontStyle,fontSize);
            text.setFont(f);
        }


        if(command.equals("Обычный"))
        {

            String fontName=f.getName();
            String fontFamily=f.getFamily();
            int fontSize=f.getSize();
            int fontStyle=f.getStyle();

            f=new Font(fontName,Font.PLAIN,fontSize);
            text.setFont(f);
        }

        if(command.equals("Полужирный"))
        {

            String fontName=f.getName();
            String fontFamily=f.getFamily();
            int fontSize=f.getSize();
            int fontStyle=f.getStyle();

            f=new Font(fontName,Font.BOLD,fontSize);
            text.setFont(f);
        }

        if(command.equals("Курсив"))
        {

            String fontName=f.getName();
            String fontFamily=f.getFamily();
            int fontSize=f.getSize();
            int fontStyle=f.getStyle();

            f=new Font(fontName,Font.ITALIC,fontSize);
            text.setFont(f);
        }

        if(command.equals("Полужирный курсив"))
        {

            String fontName=f.getName();
            String fontFamily=f.getFamily();
            int fontSize=f.getSize();
            int fontStyle=f.getStyle();

            f=new Font(fontName,Font.BOLD|Font.ITALIC,fontSize);
            text.setFont(f);
        }


        if(command.equals("12"))
        {

            String fontName=f.getName();
            String fontFamily=f.getFamily();
            int fontSize=f.getSize();
            int fontStyle=f.getStyle();

            f=new Font(fontName,fontStyle,12);
            text.setFont(f);
        }

        if(command.equals("14"))
        {

            String fontName=f.getName();
            String fontFamily=f.getFamily();
            int fontSize=f.getSize();
            int fontStyle=f.getStyle();

            f=new Font(fontName,fontStyle,14);
            text.setFont(f);
        }

        if(command.equals("18"))
        {

            String fontName=f.getName();
            String fontFamily=f.getFamily();
            int fontSize=f.getSize();
            int fontStyle=f.getStyle();

            f=new Font(fontName,fontStyle,18);
            text.setFont(f);
        }

        if(command.equals("20"))
        {
            String fontName=f.getName();
            String fontFamily=f.getFamily();
            int fontSize=f.getSize();
            int fontStyle=f.getStyle();

            f=new Font(fontName,fontStyle,20);
            text.setFont(f);
        }
    }

    public static void main(String args[])
    {
        Texteditor note = new Texteditor("Текстовый процессор");
        note.setSize(1920,1080);
        note.setVisible(true);
    }
}