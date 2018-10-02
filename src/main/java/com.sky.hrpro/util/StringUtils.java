package com.sky.hrpro.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Author: CarryJey
 * @Date: 2018/10/2 11:30:36
 */
public class StringUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtils.class);
    private static final Pattern MD5_LOWER_CASE_PATTERN = Pattern.compile("^[a-f0-9]{32}$");
    private static final Pattern MD5_UPPER_CASE_PATTERN = Pattern.compile("^[A-F0-9]{32}$");
    private static final Pattern MD5_NO_RESTRICTION_PATTERN = Pattern.compile("^[a-fA-F0-9]{32}$");
    private static final Pattern CELLPHONE_PATTERN = Pattern.compile("^1[1-9][0-9]{9}$");

    private StringUtils() {
    }

    public static Set<Long> parseIds(String ids) {
        return parseIds(ids, Long.class);
    }

    public static <T> Set<T> parseIds(String ids, Class<T> clazz) {
        return (Set) org.springframework.util.StringUtils.commaDelimitedListToSet(ids).stream().map((s) -> {
            try {
                if (clazz == String.class) {
                    if (s != null && s.trim().length() > 0) {
                        return s.trim();
                    }
                } else {
                    if (clazz == Integer.class) {
                        return Integer.valueOf(s);
                    }

                    if (clazz == Long.class) {
                        return Long.valueOf(s);
                    }
                }

                return null;
            } catch (NumberFormatException var3) {
                LOGGER.warn("Parse to long error: {}", s, var3);
                return null;
            }
        }).filter((it) -> {
            return it != null;
        }).collect(Collectors.toSet());
    }

    public static boolean isCellphone(String test) {
        return CELLPHONE_PATTERN.matcher(test).matches();
    }

    public static boolean isEmail(String test) {
        throw new UnsupportedOperationException();
    }

    public static boolean isNickname(String test) {
        throw new UnsupportedOperationException();
    }

    public static String joinWithMapping(Set<String> set, Map<String, String> mapping) {
        return set != null && set.size() != 0 ? (String)set.stream().map((it) -> {
            String code = (String)mapping.get(it);
            if (code == null) {
                throw new RuntimeException("unknown mapping for:  " + it);
            } else {
                return code;
            }
        }).collect(Collectors.joining(",")) : null;
    }

    public static Set<String> parseWithMapping(String str, Map<String, String> mapping) {
        return (Set)(!org.springframework.util.StringUtils.hasText(str) ? new HashSet() : (Set) org.springframework.util.StringUtils.commaDelimitedListToSet(str.trim()).stream().filter((it) -> {
            return it.length() > 0;
        }).map((it) -> {
            String item = (String)mapping.get(it);
            if (item == null) {
                throw new RuntimeException("unknown mapping for:  " + it);
            } else {
                return item;
            }
        }).collect(Collectors.toSet()));
    }

    public static boolean isEmpty(String test) {
        return test == null || test.length() == 0;
    }

    public static boolean isMd5(String test) {
        return MD5_NO_RESTRICTION_PATTERN.matcher(test).matches();
    }

    public static boolean isMd5Lower(String test) {
        return MD5_LOWER_CASE_PATTERN.matcher(test).matches();
    }

    public static boolean isMd5Upper(String test) {
        return MD5_UPPER_CASE_PATTERN.matcher(test).matches();
    }

    public static String maskEmail(String email) {
        if (email != null && email.length() >= 3) {
            StringBuilder sb = new StringBuilder(email);

            for(int i = 1; i < sb.length(); ++i) {
                char c = sb.charAt(i);
                if (c == '@') {
                    break;
                }

                if (i != 1) {
                    sb.setCharAt(i - 1, '*');
                }
            }

            return sb.toString();
        } else {
            return email;
        }
    }

    public static String maskCellphoe(String cellphone) {
        if (cellphone == null) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder(cellphone);

            for(int i = sb.length() - 5; i > 0; --i) {
                char c = sb.charAt(i - 1);
                if (c == ' ') {
                    break;
                }

                sb.setCharAt(i, '*');
            }

            return sb.toString();
        }
    }

    public static boolean isMacAddress(String test) {
        if (test != null && test.length() == 12) {
            for(int i = 0; i < test.length(); ++i) {
                if (!isHex(test.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public static boolean isHex(char c) {
        switch(c) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
                return true;
            case ':':
            case ';':
            case '<':
            case '=':
            case '>':
            case '?':
            case '@':
            case 'G':
            case 'H':
            case 'I':
            case 'J':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'S':
            case 'T':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
            case 'Y':
            case 'Z':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '_':
            case '`':
            default:
                return false;
        }
    }

    public static String trimToLength(String s, int length) {
        return s != null && s.length() > length ? s.substring(0, length) : s;
    }
}
