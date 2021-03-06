import React from 'react'
import HomeBookingList from './HomeBookingList'
import ReviewList from './ReviewList'

const UserProfile = ({
  user: {
    id,
    firstName,
    email,
    avgRatingHost,
    avgRatingRenter,
    hostReview,
    renterReview,
    homes,
    madeReviews,
  },
  isHost
}) => {
  return (
    <>
      {id && (
        <div>
          <h1>{id}</h1>
          <h3>{firstName}</h3>
          <h4>{email}</h4>
          <h4>{`Avg rating as host ${avgRatingHost}`}</h4>
          <h4>{`Avg rating as renter ${avgRatingRenter}`}</h4>
          <h5>Reviews as host</h5>
          {hostReview && <ReviewList reviews={hostReview} title="Reviews as Host" />}
          {renterReview && <ReviewList reviews={renterReview} title="Reviews as Renter" />}
          {madeReviews &&
            madeReviews.map((rev) => {
              return (
                <div key={rev.id}>
                  <h4>reviews made</h4>
                  <pre>{JSON.stringify(rev, null, 2)}</pre>
                </div>
              )
            })}
          <h1>Homes</h1>
          {homes &&
            homes.map((home) => {
              return (
                <div key={home.id}>
                  <h3>Price per night: {home.pricePerNight}</h3>
                  <h3>
                    Start date: {new Date(home.startDate).toLocaleDateString()}
                  </h3>
                  <h3>
                    End date: {new Date(home.endDate).toLocaleDateString()}
                  </h3>
                  {home.amenities.map((am, idx) => {
                    return <h3 key={idx}>Got {am.amenity}</h3>
                  })}
                  <h3>Street: {home.address.street}</h3>
                  <h3>City: {home.address.city}</h3>
                  <h3>Country: {home.address.country}</h3>
                  <h3>zip code: {home.address.zipCode}</h3>
                  {home.images &&
                    home.images.map((img) => {
                      // eslint-disable-next-line jsx-a11y/alt-text
                      return (
                        // eslint-disable-next-line jsx-a11y/alt-text
                        <img
                          style={{ height: '60px', width: '60px' }}
                          key={img.id}
                          src={img.imageUrl}></img>
                      )
                    })}
                  {isHost && home.bookingDetails.length && <HomeBookingList bookingDetails={home.bookingDetails} />}
                </div>
              )
            })}
        </div>
      )}
    </>
  )
}

export default UserProfile
