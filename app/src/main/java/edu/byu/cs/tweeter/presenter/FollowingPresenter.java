package edu.byu.cs.tweeter.presenter;

import edu.byu.cs.tweeter.model.services.FollowingService;
import edu.byu.cs.tweeter.net.request.FollowingRequest;
import edu.byu.cs.tweeter.net.response.FollowingResponse;

/**
 * The presenter for the "following" functionality of the application.
 */
public class FollowingPresenter implements FollowingService.Observer {

    private final FollowingService followingService;
    private final View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
        void followeesRetrieved(FollowingResponse followingResponse);
    }

    /**
     * Creates an instance.
     *
     * @param view the view for which this class is the presenter.
     */
    public FollowingPresenter(View view) {
        // An assertion would be better, but Android doesn't support Java assertions
        if(view == null) {
            throw new NullPointerException();
        }

        this.view = view;
        this.followingService = new FollowingService(this);
    }

    /**
     * Requests the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followees returned and to return the next set of
     * followees after any that were returned for a previous request. This is an asynchronous
     * operation.
     *
     * @param request contains the data required to fulfill the request.
     */
    public void getFollowing(FollowingRequest request) {
        followingService.getFollowees(request);
    }

    /**
     * Notifies the view when the users requested by the call to
     * {@link #getFollowing(FollowingRequest)} have been retrieved.
     *
     * @param followingResponse the response.
     */
    @Override
    public void followeesRetrieved(FollowingResponse followingResponse) {
        view.followeesRetrieved(followingResponse);
    }
}
