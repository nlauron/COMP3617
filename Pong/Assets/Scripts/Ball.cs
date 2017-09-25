using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Ball : MonoBehaviour {

    public float force = 0.0f;

	// Use this for initialization
	void Start () {
        GetComponent<Rigidbody>().AddForce(Vector3.right * force);
	}
	
	// Update is called once per frame
	void Update () {
		
	}

    private void OnCollisionEnter(Collision collision)
    {
        if (collision.gameObject.tag == "Wall")
        {
            Application.LoadLevel(Application.loadedLevel);
        }
    }
}
