package com.wish.dbdictionary.util;

import com.wish.dbdictionary.dto.ColumnInfoDTO;
import com.wish.dbdictionary.dto.TableInfoDTO;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.style.RtfFont;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExportUtil {

    private final static String EXPORT_FILE_NAME = "db.doc";

    /**
     * 导出到word
     * @param list    表数据
     * @throws Exception
     */
    public static void exportToWord(String path, List<TableInfoDTO> list) throws Exception
    {
        File folder = new File(path);
        if(!folder.exists()) {
            log.info("指定生成路径不存在，创建路径{}", path);
            folder.mkdirs();
        }
        final String file = path + EXPORT_FILE_NAME;
        Document doc = new Document(PageSize.A4);
        RtfWriter2.getInstance(doc, new FileOutputStream(file));
        doc.open();
        RtfFont contextFont = new RtfFont("宋体", 12, Font.NORMAL, Color.BLACK, Font.BOLDITALIC);
        RtfFont titleFont = new RtfFont("宋体", 11, Font.BOLD, Color.BLACK);
        Cell[] cellHeaders = new Cell[6];
        cellHeaders[0] = new Cell(new Phrase("序号", contextFont));
        cellHeaders[1] = new Cell(new Phrase("列名", contextFont));
        cellHeaders[2] = new Cell(new Phrase("类型", contextFont));
        cellHeaders[3] = new Cell(new Phrase("长度", contextFont));
        cellHeaders[4] = new Cell(new Phrase("是否可为空", contextFont));
        cellHeaders[5] = new Cell(new Phrase("描述", contextFont));
        for (TableInfoDTO t : list) {
            Paragraph title = new Paragraph(t.getTname() + " " + t.getComments());
            title.setAlignment(Element.ALIGN_CENTER);
            title.setFont(titleFont);
            doc.add(title);
            Table table = new Table(6, t.getColumns().size()+1);
            table.setAlignment(Element.ALIGN_CENTER);
            for(int i = 0; i < 6; i++)
            {
                table.addCell(cellHeaders[i]);//添加表头
            }
            List<ColumnInfoDTO> cl = t.getColumns();
            for (int i = 0; i < cl.size(); i++) {
                table.addCell(new Cell(new Phrase(String.valueOf(i+1), contextFont)));
                table.addCell(new Cell(new Phrase(cl.get(i).getColumnName(), contextFont)));
                table.addCell(new Cell(new Phrase(cl.get(i).getType(), contextFont)));
                table.addCell(new Cell(new Phrase(String.valueOf(cl.get(i).getLength()), contextFont)));
                table.addCell(new Cell(new Phrase(cl.get(i).getNullable(), contextFont)));
                table.addCell(new Cell(new Phrase(cl.get(i).getComments(), contextFont)));
            }
            doc.add(table);
            doc.add(new Paragraph());
        }
        doc.close();
        log.info("导出成功，文件目录：{}", file);
    }
}
