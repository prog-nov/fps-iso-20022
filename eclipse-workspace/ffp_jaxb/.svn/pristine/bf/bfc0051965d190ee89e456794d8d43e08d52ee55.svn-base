package com.forms.ffp.adaptor.prefix;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

public class FFPIclfpsMsgNamespacePrefixMapper extends NamespacePrefixMapper
{
	public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix)
	{
		String rtn = FFPMsgNamespacePrefixFactory._namespaceMap.get(namespaceUri) != null ? FFPMsgNamespacePrefixFactory._namespaceMap.get(namespaceUri) : suggestion;
		return rtn;
	}

	public String[] getPreDeclaredNamespaceUris()
	{
		return new String[0];
	}
}
