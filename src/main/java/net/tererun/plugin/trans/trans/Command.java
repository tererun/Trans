package net.tererun.plugin.trans.trans;

import com.google.gson.JsonObject;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("tl")) {
            if (args.length >= 3) {
                try {

                    int i=0;
                    String sentence = "";
                    for (String string : args) {
                        if (i >= 2) {
                            if (i == 2) {
                                sentence = string;
                            } else {
                                sentence = sentence + " " + string;
                            }
                        }
                        i++;
                    }
                    JSONObject jsonObject = new JSONObject((new HttpConnection("https://script.google.com/macros/s/AKfycbwRfetpe4gkpPurBI7GREEugzmL-tq826kcUtjnXKH78NoM9T1o/exec?text=" + URLEncoder.encode(sentence, "UTF-8")
                            + "&source=" + args[0] + "&target=" + args[1])).getResult());
                    Player player = (Player) sender;
                    System.out.println(jsonObject);
                    //String string = new String(jsonObject.optString("targetText", "null").getBytes(), "UTF-8");
                    player.chat("#" + jsonObject.optString("text", "null") + ChatColor.AQUA + "(" + sentence + ")");

                } catch (UnsupportedEncodingException err) {
                    err.printStackTrace();
                    return true;
                }
            } else {
                sender.sendMessage(Trans.string + ChatColor.AQUA + "Usage:");
                sender.sendMessage(Trans.string + ChatColor.AQUA + " /tl OriginalLanguage TranslatedLanguage Sentence");
                sender.sendMessage(Trans.string + ChatColor.AQUA + " Language list: ");
                sender.sendMessage(Trans.string + ChatColor.AQUA + "  Afrikaans: af Albanian: sq Amharic: am Arabic: ar Armenian: hy Azerbaijani: az Basque: eu Belarusian: be Bengali: bn Bosnian: bs Bulgarian: bg Catalan: ca Cebuano: ceb Chinese (Simplified): zh-CN or zh Chinese (Traditional): zh-TW Corsican: co Croatian: hr Czech: cs Danish: da Dutch: nl English: en Esperanto: eo Estonian: et Finnish: fi French: fr Frisian: fy Galician: gl Georgian: ka German: de Greek: el Gujarati: gu Haitian Creole: ht Hausa: ha Hawaiian: haw Hebrew: he or iw Hindi: hi Hmong: hmn Hungarian: hu Icelandic: is Igbo: ig Indonesian: id Irish: ga Italian: it Japanese: ja Javanese: jv Kannada: kn Kazakh: kk Khmer: km Korean: ko Kurdish: ku Kyrgyz: ky Lao: lo Latin: la Latvian: lv Lithuanian: lt Luxembourgish: lb Macedonian: mk Malagasy: mg Malay: ms Malayalam: ml Maltese: mt Maori: mi Marathi: mr Mongolian: mn Myanmar (Burmese): my Nepali: ne Norwegian: no Nyanja (Chichewa): ny Pashto: ps Persian: fa Polish: pl Portuguese (Portugal, Brazil): pt Punjabi: pa Romanian: ro Russian: ru Samoan: sm Scots Gaelic: gd Serbian: sr Sesothost Shona: sn Sindhi: sd Sinhala (Sinhalese): si Slovak: sk Slovenian: sl Somali: so Spanish: es Sundanese: su Swahili: sw Swedish: sv Tagalog (Filipino): tl Tajik: tg Tamil: ta Telugu: te Thai: th Turkish: tr Ukrainian: uk Urdu: ur Uzbek: uz Vietnamese: vi Welsh: cy Xhosa: xh Yiddish: yi Yoruba: yo Zulu: zu");
            }
        }
        return false;
    }
}
