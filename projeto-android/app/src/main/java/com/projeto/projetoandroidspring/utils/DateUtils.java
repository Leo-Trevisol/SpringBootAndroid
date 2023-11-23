package com.projeto.projetoandroidspring.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.widget.DatePicker;
import android.widget.EditText;

import com.projeto.projetoandroidspring.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    public static String FIRST_DATE_FIRST_YEAR = "01/01/0001";
    public static String SECOND_DATE_FIRST_YEAR = "02/01/0001";
    public static String THIRD_DATE_FIRST_YEAR = "03/01/0001";
    public static String FIRST_DATE_SECOND_YEAR = "01/01/0002";
    public static String LAST_DATE_LAST_YEAR = "31/12/2070";

    public static String DEFAULT_PATTERN_DATE = "dd/MM/yyyy";

    public static String PATTERN_DATE_YEAR_MONTH_DAY = "yyyyMMdd";
    public static String PATTERN_DATE_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND = "yyyyMMdd'T'HHmmss";

    public static String PATTERN_DATE_YEAR_MONTH = "yyyyMM";

    public static String PATTERN_DATE_MONTH_YEAR_FORMATTED = "MM/yyyy";

    public static String PATTERN_DATE_MONTH_FORMATTED = "dd/MM";

    public static String DEFAULT_PATTERN_DATE_TIME = "dd/MM/yyyy HH:mm:ss";

    public static String PATTERN_DATE_TIME_NO_SECONDS = "dd/MM/yyyy HH:mm";

    public static String PATTERN_DATE_MONTH_YEAR_DESC = "dd MMM. 'de' yyyy";

    public static String LEGACY_PATTERN_HOUR = "HHmm";
    public static String LEGACY_PATTERN_HOUR_MIN_SEC = "HHmmss";

    public static String PATTERN_HOUR = "HH:mm";

    public static String DEFAULT_START_PATTERN_HOUR = "00:00:00";

    public static String DEFAULT_END_PATTERN_HOUR = "23:59:59";

    public static String WHITE_SPACE = " ";

    public static String PATTERN_UTF = "yyyy-MM-dd'T'HH:mm:ss";
    private static DatePickerDialog picker;


    /**
     * Método que verifica se a data Informada é valida
     *
     * @return
     */
    public static boolean isValid(String dataText, String pattern){
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            format.setLenient(false);
            Date data = format.parse(dataText);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Método que retorna 01/01/0001
     *
     * @return
     */
    public static Date getFirstDateFirstYear() {
        return getParseDate(FIRST_DATE_FIRST_YEAR);
    }


    /** Método que retorna 02/01/0001
     *
     * @return
     */
    public static Date getSecondDateFirstYear() {
        return getParseDate(SECOND_DATE_FIRST_YEAR);
    }

    /**
     * Método que retorna 03/01/0001
     *
     * @return
     */
    public static Date getThirdDateFirstYear() {
        return getParseDate(THIRD_DATE_FIRST_YEAR);
    }

    /**
     * Método que retorna 01/01/0002
     *
     * @return
     */
    public static Date getFirstDateSecondYear() {
        return getParseDate(FIRST_DATE_SECOND_YEAR);
    }

    /**
     * Formata datas conforme padrão
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String getFormat(Date date, String pattern) {
        try {
            return new SimpleDateFormat(pattern).format(date);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * Formata a data/hora atual conforme padão.
     *
     * @param pattern
     * @return
     */
    public static String getCurrentFormat(String pattern) {
        return getFormat(new Date(), pattern);
    }

    /**
     * Formata apenas a data no formato dd/mm/yyyy
     *
     * @param date
     * @return
     */
    public static String getFormatDate(Date date) {
        return getFormat(date, DEFAULT_PATTERN_DATE);
    }

    /**
     * Formata apenas a data no formato mm/yyyy
     *
     * @param date
     * @return
     */
    public static String getFormatDateMonthYear(Date date) {
        return getFormat(date, PATTERN_DATE_MONTH_YEAR_FORMATTED);
    }

    /**
     * Formata apenas a data no formato dd/mm/yyyy se for data 01/01/0001
     * retorna empty
     *
     * @param date
     * @return
     */
    public static String getFormatDateWithoutFirstDate(Date date) {
        if (isAfterFirstDateFirstYear(date)) {
            return getFormat(date, DEFAULT_PATTERN_DATE);
        }
        return "";
    }

    /**
     * Faz o parse da string
     *
     * @param s
     * @param pattern
     * @return
     */
    public static Date getParse(String s, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(s);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * Formata apenas a data no formato yyyymmdd
     *
     * @param date
     * @return
     */
    public static String getFormatDateYearMonthDay(Date date) {
        return getFormat(date, PATTERN_DATE_YEAR_MONTH_DAY);
    }

    /**
     * Formata apenas a data/hora no formato yyyymmddhhmmss
     *
     * @param date
     * @return
     */
    public static String getFormatDateYearMonthDayHourMinuteSecond(Date date) {
        return getFormat(date, PATTERN_DATE_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND);
    }

    /**
     * Formata apenas a data no formato yyyyMM
     *
     * @param date
     * @return
     */
    public static String getFormatDateYearMonth(Date date) {
        return getFormat(date, PATTERN_DATE_YEAR_MONTH);
    }

    /**
     * Formata apenas a hora no formato HH:mm ex: 21:30 , 14:15
     *
     * @param date
     * @return
     */
    public static String getFormatHour(Date date) {
        return getFormat(date, PATTERN_HOUR);
    }

    /**
     * Formata apenas a data no formato dd/mm/yyyy
     *
     * @param s
     * @return
     */
    public static Date getParseDate(String s) {
        return getParse(s, DEFAULT_PATTERN_DATE);
    }

    /**
     * Converte texto no padrão dd/MM/yyyy HH:mm:ss para Date
     *
     * @param s
     * @return
     */
    public static Date getParseDateTime(String s) {
        return getParse(s, DEFAULT_PATTERN_DATE_TIME);
    }

    /**
     * Converte texto no padrão dd/MM/yyyy HH:mm para Date
     *
     * @param s
     * @return
     */
    public static Date getParseDateTimeNoSeconds(String s) {
        return getParse(s, PATTERN_DATE_TIME_NO_SECONDS);
    }

    /**
     * Converte o texto, no formato yyyymmdd, para data
     *
     * @param s
     * @return
     */
    public static Date getParseDateYearMonthDay(String s) {
        return getParse(s, PATTERN_DATE_YEAR_MONTH_DAY);
    }

    /**
     * Converte o texto, no formato yyyymmddhhmmss, para data/hora
     *
     * @param s
     * @return
     */
    public static Date getParseDateYearMonthDayHourMinuteSecond(String s) {
        return getParse(s, PATTERN_DATE_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND);
    }

    /**
     * Formata apenas a data no formato yyyy-MM-dd'T'HH:mm:ss
     *
     * @param s String com a data no formato UTF.Ex: 2022-02-08T08:53:47.317-03:00
     * @return
     */
    public static Date getParseDateUtf(String s) {
        return getParse(s, PATTERN_UTF);
    }

    /**
     * Formata apenas a data no formato dd/MM/yyyy HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String getFormatDateTime(Date date) {
        return getFormat(date, DEFAULT_PATTERN_DATE_TIME);
    }

    /**
     * Formata apenas a data no formato dd/MM/yyyy HH:mm
     *
     * @param date
     * @return
     */
    public static String getFormatDateTimeWithoutSeconds(Date date) {
        return getFormat(date, PATTERN_DATE_TIME_NO_SECONDS);
    }

    /**
     * Formata a data no formato dd/MM/yyyy HH:mm quando recebemos dois campos
     * separados sendo um de data e outro de horaMinuto
     *
     * @param date
     * @param hour
     * @return
     */
    public static String getFormatDateTime(Date date, String hour) {
        try {
            return getFormatDate(date) + " " + hour.substring(0, 2) + ":" + hour.substring(2);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * Altera data para ter time 00:00:00
     *
     * @param date
     * @return
     */
    public static Date getDateWithTimeAtStartOfDay(Date date) {
        try {
            String s = getFormat(date, DEFAULT_PATTERN_DATE);
            Date newDate = new SimpleDateFormat(DEFAULT_PATTERN_DATE_TIME).parse(s + WHITE_SPACE + DEFAULT_START_PATTERN_HOUR);
            return newDate;
        } catch (Exception e) {
        }
        return date;
    }

    /**
     * Retorna amanhã com time 00:00:00
     *
     *
     * @return
     */
    public static Date getTomorrowWithTimeAtStartOfDay() {
        try {
            Date newDate = getDateWithPlusDays(new Date(), 1);
            newDate = getDateWithTimeAtStartOfDay(newDate);
            return newDate;
        } catch (Exception e) {
        }
        return new Date();
    }

    /**
     * Retorna ontem com time 00:00:00
     *
     *
     * @return
     */
    public static Date getYesterdayWithTimeAtStartOfDay() {
        try {
            Date newDate = getDateWithMinusDaysWithTimeAtStartOfDay(new Date(), 1);
            return newDate;
        } catch (Exception e) {
        }
        return new Date();
    }

    /**
     * Retorna ontem com time 23:59:59
     *
     *
     * @return
     */
    public static Date getYesterdayWithTimeAtEndOfDay() {
        try {
            Date newDate = getDateWithMinusDays(new Date(), 1);
            newDate = getDateWithTimeAtEndOfDay(newDate);
            return newDate;
        } catch (Exception e) {
        }
        return new Date();
    }

    /**
     * Retorna data atual sem hora
     *
     *
     * @return
     */
    public static Date getCurrentDateWithoutHour() {
        Date newDate = new Date();
        return getDateWithoutHour(newDate);
    }

    public static Date getDateWithoutHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * Altera data para ter time 23:59:59
     *
     * @param date
     * @return
     */
    public static Date getDateWithTimeAtEndOfDay(Date date) {
        try {
            Date newDate = new SimpleDateFormat(DEFAULT_PATTERN_DATE_TIME).parse(getFormatDate(date) + WHITE_SPACE + DEFAULT_END_PATTERN_HOUR);
            return newDate;
        } catch (Exception e) {
        }
        return date;
    }

    /**
     * Diminui dias de data
     *
     * @param date
     * @param days
     * @return
     */
    public static Date getDateWithMinusDays(Date date, int days) {
        Calendar cal = getCalendarWithDate(date);
        cal.add(Calendar.DATE, -days);
        return cal.getTime();
    }

    /**
     * Diminui meses de data
     *
     * @param date
     * @param months
     * @return
     */
    public static Date getDateWithMinusMonths(Date date, int months) {
        Calendar cal = getCalendarWithDate(date);
        Boolean isLastDayOfMonth = cal.get(Calendar.DAY_OF_MONTH) == cal.getMaximum(Calendar.DAY_OF_MONTH);
        cal.add(Calendar.MONTH, -months);
        if (isLastDayOfMonth) {
            cal.set(Calendar.DAY_OF_MONTH, cal.getMaximum(Calendar.DAY_OF_MONTH));
        }
        return cal.getTime();
    }

    /**
     * Seta dia do mês específico para data
     *
     * @param date
     * @param day
     * @return
     */
    public static Date getDateWithSpecificDayOfMonth(Date date, int day) {
        Calendar cal = getCalendarWithDate(date);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    /**
     * Diminui minutos da data
     *
     * @param date
     * @param minutes
     * @return
     */
    public static Date getDateWithMinusMinutes(Date date, int minutes) {
        Calendar cal = getCalendarWithDate(date);
        cal.add(Calendar.MINUTE, -minutes);
        return cal.getTime();
    }

    /**
     * Calcular e retornar em dias a diferença entre duas datas, a primeira
     * menos a segunda.
     *
     * @param date1
     * @param date2
     * @return
     */
    public static Long getDifferenceBetweenDateInDays(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0l;
        }
        return ((date1.getTime() - date2.getTime()) / 1000L / 60L / 60L / 24L);
    }

    /**
     * Diminui dias de data e altera data para ter time 00:00:00
     *
     * @param date
     * @param days
     * @return
     */
    public static Date getDateWithMinusDaysWithTimeAtStartOfDay(Date date, int days) {
        Date d = getDateWithMinusDays(date, days);
        d = getDateWithTimeAtStartOfDay(d);
        return d;
    }

    /**
     * Aumenta horas na data informada
     *
     * @param date
     * @param hours
     * @return
     */
    public static Date getDateWithPlusHours(Date date, long hours) {
        return getDateWithPlusHours(date, (int) hours);
    }

    /**
     * Aumenta horas na data informada
     *
     * @param date
     * @param hours
     * @return
     */
    public static Date getDateWithPlusHours(Date date, int hours) {
        Calendar cal = getCalendarWithDate(date);
        cal.add(Calendar.HOUR, hours);
        return cal.getTime();
    }

    /**
     * Aumenta dias de data
     *
     * @param date
     * @param days
     * @return
     */
    public static Date getDateWithPlusDays(Date date, int days) {
        Calendar cal = getCalendarWithDate(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    /**
     * Adiciona meses a data
     *
     * @param date
     * @param months
     * @return
     */
    public static Date getDateWithPlusMonths(Date date, int months) {
        Calendar cal = getCalendarWithDate(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * Aumenta dias de data
     *
     * @param date
     * @param years
     * @return
     */
    public static Date getDateWithPlusYears(Date date, int years) {
        Calendar cal = getCalendarWithDate(date);
        cal.add(Calendar.YEAR, years);
        return cal.getTime();
    }

    /**
     * Aumenta segundos de data
     *
     * @param date
     * @param seconds
     * @return
     */
    public static Date getDateWithPlusSeconds(Date date, int seconds) {
        Calendar cal = getCalendarWithDate(date);
        cal.add(Calendar.SECOND, seconds);
        return cal.getTime();
    }

    /**
     * Aumenta dias de data com time 23:59:59
     *
     * @param date
     * @param days
     * @return
     */
    public static Date getDateWithPlusDaysWithTimeAtEndOfDay(Date date, int days) {
        Date newDate = getDateWithPlusDays(date, days);
        newDate = getDateWithTimeAtEndOfDay(newDate);
        return newDate;
    }

    /**
     * Aumenta dias de data com time 23:59:59
     *
     * @param date
     * @param years
     * @return
     */
    public static Date getDateWithPlusYearsWithTimeAtEndOfDay(Date date, int years) {
        Date newDate = getDateWithPlusYears(date, years);
        newDate = getDateWithTimeAtEndOfDay(newDate);
        return newDate;
    }

    /**
     * Aumenta dias de data com time 00:00:00
     *
     * @param date
     * @param days
     * @return
     */
    public static Date getDateWithPlusDaysWithTimeAtStartOfDay(Date date, int days) {
        Date newDate = getDateWithPlusDays(date, days);
        newDate = getDateWithTimeAtEndOfDay(newDate);
        return newDate;
    }

    /**
     * Retorna o primeiro dia do mês corrente
     *
     * @return
     */
    public static Date getFirstDateInCurrentMonth() {
        Calendar cal = getCalendarWithCurrentDateWithoutHour();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * Retorna o último dia do mês corrente
     *
     * @return
     */
    public static Date getLastDateInCurrentMonth() {
        Calendar cal = getCalendarWithCurrentDateWithoutHour();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    /**
     * Retorna o primeiro dia do mês da data informada
     *
     * @param date
     * @return
     */
    public static Date getFirstDateInMonthByDate(Date date) {
        Calendar cal = getCalendarWithDate(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * Retorna hora corrente no formato do legado HHMM exemplo 2215, 1845,1230
     *
     * @return
     */
    public static String getCurrentHourLegacyFormat() {
        return getCurrentFormat(LEGACY_PATTERN_HOUR);
    }

    /**
     * Retorna hora corrente no formato do legado HHMMSS exemplo 221530,
     * 184550,123023
     *
     * @return
     */
    public static String getCurrentHourWithSecondsLegacyFormat() {
        return getCurrentFormat(LEGACY_PATTERN_HOUR_MIN_SEC);
    }

    /**
     * Retorna se data é igual a 01/01/0001
     *
     * @param date
     * @return
     */
    public static boolean isFirstDateFirstYear(Date date) {
        return date != null && getFormatDate(date).equals(FIRST_DATE_FIRST_YEAR);
    }

    /**
     * Retorna se data é maior que 01/01/0001
     *
     * @param date
     * @return
     */
    public static boolean isAfterFirstDateFirstYear(Date date) {
        return date != null && date.compareTo(getFirstDateFirstYear()) > 0;
    }

    /**
     * Retorna calendar com data atual
     *
     * @return
     */
    private static Calendar getCalendarWithCurrentDate() {
        return getCalendarWithDate(new Date());
    }

    /**
     * Retorna calendar com data atual sem hora e minutos
     *
     * @return
     */
    private static Calendar getCalendarWithCurrentDateWithoutHour() {
        return getCalendarWithDate(getCurrentDateWithoutHour());
    }

    /**
     * Retorna calendar com data
     *
     * @param date
     * @return
     */
    public static Calendar getCalendarWithDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static boolean isDayOfWeek(Date date, int day) {
        int dayDate = 1;
        if (date != null) {
            dayDate = getCalendarWithDate(date).get(Calendar.DAY_OF_WEEK);
        }
        return day == dayDate;
    }

    /**
     * Método que retorna se data é domingo
     *
     * @param date
     * @return
     */
    public static boolean isSunday(Date date) {
        return isDayOfWeek(date, Calendar.SUNDAY);
    }

    /**
     * Método que retorna se data é segunda
     *
     * @param date
     * @return
     */
    public static boolean isMonday(Date date) {
        return isDayOfWeek(date, Calendar.MONDAY);
    }

    /**
     * Método que retorna se data é domingo ou segunda
     *
     * @param date
     * @return
     */
    public static boolean isSundayOrMonday(Date date) {
        return isDayOfWeek(date, Calendar.SUNDAY) || isDayOfWeek(date, Calendar.MONDAY);
    }

    public static boolean isDateBetweenBeginDateEndDate(Date data, Date beginDate, Date endDate) {
        return !(data.compareTo(beginDate) == -1 || data.compareTo(endDate) == 1);
    }

    /**
     * Método que retorna se primeira data vem antes da segunda data
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isBefore(Date date1, Date date2) {
        return date1 != null && date2 != null && date1.before(date2);
    }

    /**
     * Método que retorna se primeira data vem depois da segunda data
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isAfter(Date date1, Date date2) {
        return date1 != null && date2 != null && date1.after(date2);
    }

    /**
     * Método que retorna se a primeira data vem depois ou é igual a segunda
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isAfterOrEqual(Date date1, Date date2) {
        return isAfter(date1, date2) || isEqual(date1, date2);
    }

    /**
     * Método que retorna se a primeira data vem antes ou é igual a segunda
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isBeforeOrEqual(Date date1, Date date2) {
        return isBefore(date1, date2) || isEqual(date1, date2);
    }

    /**
     * Método que retorna se as datas informadas são iguais. O método zera as
     * horas e minutos para fazer comparação
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isEqual(Date date1, Date date2) {
        return date1 != null && date2 != null && getDateWithTimeAtStartOfDay(date1).equals(getDateWithTimeAtStartOfDay(date2));
    }

    /**
     * Método que retorna se primeira data vem depois da data atual
     *
     * @param date
     * @return
     */
    public static boolean isAfterCurrentDate(Date date) {
        return date != null && date.after(new Date());
    }

    /**
     * Método que retorna se a data informada é igual a data atual. O método
     * zera as horas e minutos para fazer comparação
     *
     * @param date
     * @return
     */
    public static boolean isEqualCurrentDate(Date date) {
        return date != null && getDateWithTimeAtStartOfDay(date).equals(getDateWithTimeAtStartOfDay(new Date()));
    }

    /**
     * Método que retorna se primeira data vem antes da data atual
     *
     * @param date
     * @return
     */
    public static boolean isBeforeCurrentDate(Date date) {
        return date != null && date.before(new Date());
    }

    /**
     * Método que retorna se primeira data vem antes de hoje
     *
     * @param date
     * @return
     */
    public static boolean isBeforeToday(Date date) {
        return date != null && date.before(getDateWithTimeAtStartOfDay(new Date()));
    }

    /**
     * Converte um objeto do tipo Date em uma data no formato unix. Data em
     * formato unix é o número de segundos contados a partir de
     * 1970-01-01T00:00:00Z.
     *
     * @param date
     * @return
     */
    public static String convertDateToUnixDate(Date date) {
        if (date != null) {
            Long seconds = date.getTime() / 1000;
            return seconds.toString();
        }
        return null;
    }

    /**
     * Método que retorna o ano ex: 1990,1980
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        return date != null ? getCalendarWithDate(date).get(Calendar.YEAR) : 0;
    }

    /**
     * Método que retorna o ano no século ex: 99,70
     *
     * @param date
     * @return
     */
    public static int getYearOfCentury(Date date) {
        String year = getYear(date) + "";
        return Integer.parseInt(year.length() == 4 ? year.substring(2) : year);
    }

    /**
     * Método que retorna o mês do ano de 0 a 11 ex: janeiro = 0 dezembro = 11.
     *
     * @param date
     * @return
     */
    public static int getMonthOfYear(Date date) {
        return date != null ? getCalendarWithDate(date).get(Calendar.MONTH) : 0;
    }

    /**
     * Método que retorna o mês e o ano abreviados ex: JAN. 20 DEZ. 95.
     *
     * @param date
     * @return
     */
    public static String getMonthAndYear(Date date) {
        if (date != null) {
            Calendar calendar = getCalendarWithDate(date);
            DateFormat dateFormat = new SimpleDateFormat("MMM", new Locale("pt", "BR"));
            return dateFormat.format(calendar.getTime()).toUpperCase()+" "+getYearOfCentury(date);
        }
        return "";
    }

    /**
     * Método que retorna uma data com os valores informados como parâmetro.
     *
     * @param year
     * @param month
     * @param date
     * @return
     */
    public static Date getDate(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * Método que retorna o dia da data ex: 05/02/2018 = 5.
     *
     * @param date
     * @return
     */
    public static int getDayOfDate(Date date) {
        return date != null ? getCalendarWithDate(date).get(Calendar.DAY_OF_MONTH) : 0;
    }

    /**
     * Retorna a diferença do número de dias entre as datas
     */
    public static Integer diffInDays(Date date1, Date date2) {
        Long dias = 0l;
        dias = TimeUnit.DAYS.convert(diff(date1, date2), TimeUnit.MILLISECONDS);
        return dias.intValue();
    }

    /**
     * Retorna a diferença do número de horas entre as datas
     */
    public static Integer diffInHours(Date date1, Date date2) {

        if (date1 == null || date2 == null)
            return 0;

        Long dias = 0l;
        dias = TimeUnit.HOURS.convert(diff(date1, date2), TimeUnit.MILLISECONDS);
        return dias.intValue();
    }

    public static Integer diffInMonth(Date date1, Date date2) {
        Long diffMonth = 0l;
        if (date1 != null && date2 != null) {
            Calendar startCalendar = Calendar.getInstance();
            Calendar endCalendar = Calendar.getInstance();
            if (date2.compareTo(date1) > 0) {
                startCalendar.setTime(date1);
                endCalendar.setTime(date2);
            } else {
                startCalendar.setTime(date2);
                endCalendar.setTime(date1);
            }
            long diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
            diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
        }
        return diffMonth.intValue();
    }

    public static Integer diffInYars(Date date1, Date date2) {
        Long diffYear = diffInMonth(date1, date2) / 12l;
        return diffYear.intValue();
    }

    public static long diff(Date date1, Date date2) {
        long diff = 0l;
        if (date1 != null && date2 != null) {
            if (date2.compareTo(date1) > 0)
                diff = date2.getTime() - date1.getTime();
            else
                diff = date1.getTime() - date2.getTime();
        }
        return diff;
    }

    public static Action0 actionCalendar(EditText editText, Context context) {

        return new Action0() {
            @Override
            public void call() {
                int day, month, year;
                if(DateUtils.isValid(editText.getText().toString(), DateUtils.DEFAULT_PATTERN_DATE) && editText.getText().length() == 10){
                    Date date = DateUtils.getParseDate(editText.getText().toString());
                    day = DateUtils.getDayOfDate(date);
                    month = DateUtils.getMonthOfYear(date);
                    year = DateUtils.getYear(date);
                } else {
                    final Calendar cldr = Calendar.getInstance();
                    day = cldr.get(Calendar.DAY_OF_MONTH);;
                    month = cldr.get(Calendar.MONTH);
                    year = cldr.get(Calendar.YEAR);
                }

                picker = new DatePickerDialog(context, R.style.DatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Date dateSelected = DateUtils.getDate(year, month, dayOfMonth);
                        editText.setText(DateUtils.getFormatDate(dateSelected));
                    }

                }, year, month, day);
                if(!Utils.isEmpty(editText)){
                    picker.setButton(DatePickerDialog.BUTTON_NEUTRAL, "LIMPAR", new DatePickerDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            editText.setText("");
                        }
                    });
                }
                picker.show();

                picker.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                picker.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                if(picker.getButton(DatePickerDialog.BUTTON_NEUTRAL) != null) {
                    picker.getButton(DatePickerDialog.BUTTON_NEUTRAL).setTextColor(Color.BLACK);
                }}
        };
    }

    public static boolean isValidDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            sdf.setLenient(false);
            Date date = sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }


}

