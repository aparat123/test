package ru.euphoriadev.vk.api.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import ru.euphoriadev.vk.api.Api;

public class VKPoll implements Serializable {
    private static final long serialVersionUID = 1L;
    public long id;
    public String question;
    public long owner_id;
    public Long created;
    public Long votes;
    public Long answer_id;
    public String answers_json;
    public boolean anonymous;
    public Long topic_id;//if poll is attached to topic

    public static VKPoll parse(JSONObject o) {
        VKPoll v = new VKPoll();
        v.id = o.optLong("id");
        v.question = Api.unescape(o.optString("question"));
        if (o.has("owner_id"))
            v.owner_id = o.optLong("owner_id");
        if (o.has("created"))
            v.created = o.optLong("created");
        if (o.has("votes"))
            v.votes = o.optLong("votes");
        if (o.has("answer_id"))
            v.answer_id = o.optLong("answer_id");
        if (o.has("answers"))
            v.answers_json = o.optJSONArray("answers").toString();
        if (o.has("anonymous"))
            v.anonymous = o.optString("anonymous").equals("1");
        return v;
    }

    public static ArrayList<VkPollAnswer> getPollAnswers(String answers_json) {
        ArrayList<VkPollAnswer> answers = new ArrayList<VkPollAnswer>();
        try {
            JSONArray array = new JSONArray(answers_json);
            for (int i = 0; i < array.length(); ++i) {
                if (array.get(i) instanceof JSONObject == false)
                    continue;
                JSONObject o = (JSONObject) array.get(i);
                VkPollAnswer pa = new VkPollAnswer();
                pa.id = o.getLong("id");
                pa.votes = o.getInt("votes");
                pa.text = Api.unescape(o.getString("text"));
                pa.rate = o.getInt("rate");
                answers.add(pa);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return answers;
    }
}