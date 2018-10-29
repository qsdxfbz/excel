package markting;

import org.apache.ibatis.io.ResolverUtil.Test;

import com.github.pagehelper.util.StringUtil;
import com.mukitech.web.util.StringUtils;

public class test {
	public static void main(String[] args) {
		String date = "05-一月-2018";
		
		String sb = StringUtils.transFormat2(date);
		System.out.println(sb);
		
		String sb1 = StringUtils.appendSerialnum(sb);
		System.out.println(sb1);
		
	}
}
