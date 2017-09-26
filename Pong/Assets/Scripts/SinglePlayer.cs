using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class SinglePlayer : MonoBehaviour {

    public void changeToSingle(int changeScene)
    {
        SceneManager.LoadScene(changeScene);
    }
}
