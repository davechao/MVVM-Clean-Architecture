package com.test.mvvm.utility

/**
 * Ref:
 * https://github.com/eclipse/egit-github/blob/master/org.eclipse.egit.github.core/src/org/eclipse/egit/github/core/client/PageLinks.java#L43-75
 *
 */
enum class ResponseUtil {
    INSTANCE;

    companion object {
        const val SEPARATOR_LINKS = ","
        const val SEPARATOR_LINK_PARAM = ";"
        const val META_NEXT = "next"
        const val META_REL = "rel"
        const val HEADER_KEY_LINK = "Link"
    }

    fun parseNextPageUrl(headers: String?): String {
        headers?.run {
            val links = headers.split(SEPARATOR_LINKS)
            for (link in links) {
                val segments =
                    link.split(SEPARATOR_LINK_PARAM.toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                if (segments.size < 2)
                    continue

                var linkPart = segments[0].trim { it <= ' ' }
                if (!linkPart.startsWith("<") || !linkPart.endsWith(">"))
                //$NON-NLS-1$ //$NON-NLS-2$
                    continue
                linkPart = linkPart.substring(1, linkPart.length - 1)

                for (i in 1 until segments.size) {
                    val rel = segments[i].trim { it <= ' ' }.split("=".toRegex())
                        .dropLastWhile { it.isEmpty() }
                        .toTypedArray() //$NON-NLS-1$
                    if (rel.size < 2 || META_REL != rel[0])
                        continue

                    var relValue = rel[1]
                    if (relValue.startsWith("\"") && relValue.endsWith("\""))
                    //$NON-NLS-1$ //$NON-NLS-2$
                        relValue = relValue.substring(1, relValue.length - 1)

                    if (META_NEXT == relValue)
                        return linkPart
                }
            }
        }
        return ""
    }
}