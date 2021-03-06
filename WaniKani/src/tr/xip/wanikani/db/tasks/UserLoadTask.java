package tr.xip.wanikani.db.tasks;

import android.content.Context;
import android.os.AsyncTask;

import tr.xip.wanikani.api.response.User;
import tr.xip.wanikani.db.DatabaseManager;
import tr.xip.wanikani.db.tasks.callbacks.UserLoadTaskCallbacks;

/**
 * Created by Hikari on 1/7/15.
 */
public class UserLoadTask extends AsyncTask<Void, Void, User> {

    private Context context;

    private UserLoadTaskCallbacks mCallbacks;

    public UserLoadTask(Context context, UserLoadTaskCallbacks callbacks) {
        this.context = context;
        this.mCallbacks = callbacks;
    }

    public void executeSerial() {
        execute();
    }

    public void executeParallell() {
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    protected User doInBackground(Void... params) {
        return new DatabaseManager(context).getUser();
    }

    @Override
    protected void onPostExecute(User user) {
        super.onPostExecute(user);

        if (mCallbacks != null)
            mCallbacks.onUserLoaded(user);
    }
}