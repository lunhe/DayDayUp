package com.helun.menu.util;


import java.text.*;

import static java.lang.System.*;

public class StringAlign extends Format {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/* 枚举，哪种对齐方式 */
	public enum Alignment {

		/* 左对齐 */

		LEFT,

		/* 居中对齐 */

		CENTER,

		/* 右对齐 */

		RIGHT,

	}

	private Alignment aligment;// 当前对齐

	private int maxPages;// 当前最大长度

	/* 构造方法，用来设置字符串的居中方式以及最大长度 */

	public StringAlign(int maxPages, Alignment alignment) {

		switch (alignment) {

		case LEFT:

		case CENTER:

		case RIGHT:

			this.aligment = alignment;// 将传过来的对齐方式赋值给全局的alignment变量

			break;

		default:

			throw new IllegalArgumentException("对齐参数错误！");

		}

		if (maxPages < 0) {// 长度为负数时会抛出异常

			throw new IllegalArgumentException("页数参数错误");

		}

		this.maxPages = maxPages;

	}

	public static void main(String[] args) {

		// TODO Auto-generated method stub

		out.println("房东电话给一还是对方国家偶数个佛山东方宫is东方精工静极思动if个");// 随便打的字，只是用来测试


		StringAlign align = new StringAlign(50, StringAlign.Alignment.LEFT);// 调用构造方法，设置字符串对齐为居中对齐，最大长度为50

		out.print(align.format("告诉其他人谁抢答成功"));

		out.println(align.format("发送{\"studentId\":\"\",\"socketGroupId\":\"600000000000009\",\"currentType\":\"\",\"name\":\"\",\"avatar\":\"\",\"userType\""));
		
		out.print(align.format("退出抢答界面"));

		out.println(align.format("发送{\"socketGroupId\":\"600000000000009\",\"userId\":\"800000000001013\",\"key\":44005}"));
		
		out.print(align.format("- i -"));

		out.println(align.format(Integer.toString(10)));

	}

	@Override

	public StringBuffer format(Object input, StringBuffer where, FieldPosition ignore) {

		// TODO Auto-generated method stub

		String s = input.toString();

		String wanted = s.substring(0, Math.min(s.length(), maxPages));

		// 得到右侧的空格
		int length = countSpaceLength(wanted);
		switch (aligment) {

		case RIGHT:

			pad(where, length);

			where.append(wanted);

			break;

		case CENTER:

			int toAdd = length;

			pad(where, toAdd / 2);

			where.append(wanted);

			pad(where, toAdd - toAdd / 2);

			break;

		case LEFT:

			where.append(wanted);

			pad(where, length);

			break;

		}

		return where;

	}
	
	private Integer countSpaceLength(String wanted) {
		int length = maxPages ;
		for(char c : wanted.toCharArray()) {
			if(isChinese(c)) {
				length -= 2 ;
			}else {
				length -- ;
			}
		}
		return length ;
	}

	private void pad(StringBuffer where, int howMany) {

		// TODO Auto-generated method stub

		for (int i = 0; i < howMany; i++) {

			where.append(' ');// 添加空格

		}

	}

	public String format(Integer length , String s) {
		this.maxPages = length;
		return format(s, new StringBuffer(), null).toString();
	}
	
	String format(String s) {

		return format(s, new StringBuffer(), null).toString();

	}

	@Override

	public Object parseObject(String source, ParsePosition pos) {// 用处不大

		// TODO Auto-generated method stub

		return source;

	}
	
    public  boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

}
