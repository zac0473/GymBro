package persistence;

import org.json.JSONObject;

// from example
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
