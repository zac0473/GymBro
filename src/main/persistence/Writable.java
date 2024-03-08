package persistence;

import org.json.JSONObject;

// Citation: Some code borrowed from
// JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
